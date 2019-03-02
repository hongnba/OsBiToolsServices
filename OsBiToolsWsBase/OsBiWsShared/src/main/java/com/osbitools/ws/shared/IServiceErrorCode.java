package com.osbitools.ws.shared;

/**
 * Interface for service error code. It depends of layer and could be HTTP Error code or 
 *  SQL Error code or any other.
 *  
 */
public interface IServiceErrorCode {

  /**
   * Get Service Error Code
   * 
   * @return Service Error Code
   */
  int getServiceErrorCode();
}
