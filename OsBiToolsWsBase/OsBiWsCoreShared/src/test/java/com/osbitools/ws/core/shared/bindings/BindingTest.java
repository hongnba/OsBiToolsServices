/*
 * Copyright 2014-2018 IvaLab Inc. and by respective contributors (see below).
 * 
 * Released under the LGPL v3 or higher
 * See http://www.gnu.org/licenses/lgpl-3.0.html
 *
 * Date: 2014-11-07
 * 
 * Contributors:
 * 
 */

package com.osbitools.ws.core.shared.bindings;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.junit.BeforeClass;
import org.junit.Test;

import com.osbitools.ws.core.shared.common.CoreSharedTestConstants;
import com.osbitools.ws.shared.*;
import com.osbitools.ws.shared.common.JarTestResourceUtils;
import com.osbitools.ws.shared.common.JarUtils;
import com.osbitools.ws.shared.binding.ds.*;
import com.osbitools.ws.shared.binding.ll_set.*;

/**
 * Unit Test for binding xml files
 * 
 */
public class BindingTest {

  // Handle for XML Unmarshaller
  private static Unmarshaller _ll;
  private static Unmarshaller _ds;

  // Test locales
  private static final String[] _tlangs = new String[] { "en", "fr", "ru" };

  private static DsMapTestResConfig _ds_cfg = new DsMapTestResConfig();

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    _ll =
        JAXBContext.newInstance(LsConstants.BIND_PKG_LANG_LABELS_SET).createUnmarshaller();

    _ds = JAXBContext.newInstance(DsConstants.BIND_PKG_DS_MAP).createUnmarshaller();
  }

  @Test
  public void testBadXml() throws IOException {
    ByteArrayInputStream bad_xml = new ByteArrayInputStream("Bad File".getBytes());
    try {
      _ll.unmarshal(bad_xml);
      fail("Exception expected.");
    } catch (JAXBException e) {}
  }

  @Test
  public void testLangLabels() throws JAXBException, IOException {

    InputStream[] flist = new InputStream[] {
        JarUtils.readJarFileAsStream(LsTestConstants.LANG_SET_RES_PATH + "_test"),
        JarUtils.readJarFileAsStream(LsTestConstants.LANG_SET_RES_PATH),
        JarUtils.readJarFileAsStream(LsTestConstants.LANG_SET_RES_PATH + "_combo") };

    LangLabelsSet[] lmap = new LangLabelsSet[flist.length];

    for (int i = 0; i < flist.length; i++) {
      InputStream f = flist[i];
      JAXBElement<?> jaxb = (JAXBElement<?>) _ll.unmarshal(f);
      LangLabelsSet ls = (LangLabelsSet) jaxb.getValue();

      lmap[i] = ls;
      testLangsDef(ls);
    }

    checkLangLabelsSetPartial(lmap[0]);
    checkLangLabelsSetFull(lmap[1]);
    checkLangLabelsSetCombined(lmap[2]);
  }

  private void testLangsDef(LangLabelsSet ls) {

    String langs = ls.getLangList();
    assertNotNull(langs);
    assertEquals("Language set doesn't match", _tlangs.length, langs.split(",").length);
  }

  public static void checkLangLabelsSetPartial(LangLabelsSet ll) {
    assertEquals("Total Size", 1, ll.getLangLabel().size());

    checkLabel(ll.getLangLabel().get(0), "LL_LETS_GO",
        new String[] { "Let's go", "Allons-y", "Поехали" });
  }

  public static void checkLangLabelsSetFull(LangLabelsSet ll) {
    assertEquals("Total Size", 2, ll.getLangLabel().size());

    checkLabel(ll.getLangLabel().get(0), "LL_USERNAME",
        new String[] { "Username", "Nom d'utilisateur", "Имя" });
    checkLabel(ll.getLangLabel().get(1), "LL_PASSWORD",
        new String[] { "Password", "Mot de passe", "Пароль" });
  }

  public static void checkLangLabelsSetCombined(LangLabelsSet ll) {
    assertEquals("Total Size", 3, ll.getLangLabel().size());

    checkLabel(ll.getLangLabel().get(0), "LL_USERNAME",
        new String[] { "Username", "Nom d'utilisateur", "Имя" });
    checkLabel(ll.getLangLabel().get(1), "LL_PASSWORD",
        new String[] { "Password", "Mot de passe", "Пароль" });
    checkLabel(ll.getLangLabel().get(2), "LL_LETS_GO",
        new String[] { "Let's go", "Allons-y", "Поехали" });
  }

  private static void checkLabel(LangLabel label, String ids, String[] lvalues) {

    assertEquals(ids, label.getId());

    for (int i = 0; i < _tlangs.length; i++) {
      LangLabelDef ld = label.getLlDef().get(i);

      assertEquals(_tlangs[i], ld.getLang());
      assertEquals(lvalues[i], ld.getValue());
    }
  }

  @Test
  public void testDs() throws JAXBException, IOException {

    String[] flist =
        new String[] { TestDsConstants.DS_DEMO_FNAME, CoreSharedTestConstants.DS_EMPTY_XML };

    HashMap<String, DataSetDescr> dmap = new HashMap<String, DataSetDescr>();

    for (String fname : flist) {
      JAXBElement<?> jaxb = (JAXBElement<?>) _ds
          .unmarshal(JarTestResourceUtils.readDemoFileAsStream(fname, _ds_cfg));
      dmap.put(fname, (DataSetDescr) jaxb.getValue());
    }

    checkDataSetDescrPartial(dmap.get(CoreSharedTestConstants.DS_EMPTY_XML));
    DsMapsTestUtils.checkDemoDataSetMap(dmap.get(TestDsConstants.DS_DEMO_FNAME));
  }

  @Test
  public void testStaticLangDs() throws JAXBException, IOException {
    JAXBElement<?> jaxb = (JAXBElement<?>) _ds.unmarshal(
        JarTestResourceUtils.readDemoFileAsStream("test/static_test_lmap.xml", _ds_cfg));

    DataSetDescr dsd = (DataSetDescr) jaxb.getValue();

    StaticData sdf = dsd.getStaticData();
    assertEquals(1, sdf.getColumns().getColumn().size());
    assertEquals(2, sdf.getStaticRows().getRow().size());

    LangMap lm = dsd.getLangMap();
    assertNotNull(lm);
    assertEquals(1, lm.getColumn().size());
  }

  @Test
  public void testStaticStr1() throws JAXBException, IOException {
    JAXBElement<?> jaxb = (JAXBElement<?>) _ds.unmarshal(
        JarTestResourceUtils.readDemoFileAsStream("test/static_test_str1.xml", _ds_cfg));

    DataSetDescr dsd = (DataSetDescr) jaxb.getValue();

    SortGroup sg = dsd.getSortByGrp();
    assertNotNull(sg);
    assertEquals(1, sg.getSortBy().size());
  }

  public static void checkDataSetDescrPartial(DataSetDescr ds) {
    assertEquals("Description", "Empty DataSet", ds.getDescr());
    assertEquals("Enabled", false, ds.isEnabled());
    assertEquals("Major Version", 1, ds.getVerMax());
    assertEquals("Minor Version", 0, ds.getVerMin());

    assertNull(ds.getLangMap());
    assertNull(ds.getExColumns());
    assertNull(ds.getFilter());
    assertNull(ds.getSortByGrp());
  }

  public static void checkDataSetDescrFull(DataSetDescr ds) {
    DsMapsTestUtils.checkDemoDataSetMap(ds);
  }
}
