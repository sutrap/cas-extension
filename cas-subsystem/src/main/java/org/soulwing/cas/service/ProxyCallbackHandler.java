/*
 * File created on Feb 21, 2015 
 *
 * Copyright (c) 2014 Virginia Polytechnic Institute and State University
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package org.soulwing.cas.service;

/**
 * A handler for proxy callbacks.
 *
 * @author Carl Harris
 */
public interface ProxyCallbackHandler {

  /**
   * Handles a proxy callback.
   * @param query query string from the proxy callback request
   * @return proxy callback response
   */
  ProxyCallbackResponse onProxyCallback(String query);
  
  /**
   * Gets the backing storage delegate.
   * @return delegate
   */
  Object getStorage();
  
}
