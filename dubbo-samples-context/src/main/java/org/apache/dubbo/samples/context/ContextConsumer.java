/*
 *   Licensed to the Apache Software Foundation (ASF) under one or more
 *   contributor license agreements.  See the NOTICE file distributed with
 *   this work for additional information regarding copyright ownership.
 *   The ASF licenses this file to You under the Apache License, Version 2.0
 *   (the "License"); you may not use this file except in compliance with
 *   the License.  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package org.apache.dubbo.samples.context;

import org.apache.dubbo.samples.context.api.ContextService;

import com.alibaba.dubbo.rpc.RpcContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ContextConsumer {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"spring/dubbo-context-consumer.xml"});
        context.start();
        ContextService contextService = (ContextService) context.getBean("demoService"); // get remote service proxy

        String hello = contextService.sayHello("world"); // call remote method

        boolean isConsumerSide = RpcContext.getContext().isConsumerSide();
        String application = RpcContext.getContext().getUrl().getParameter("application");
        String serverIP = RpcContext.getContext().getRemoteHost();

        System.out.println(hello); // get result

    }
}
