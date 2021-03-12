/*
 * Copyright 2018 Kaya Kupferschmidt
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dimajix.flowman.example.plugin

import com.dimajix.flowman.execution.Context
import com.dimajix.flowman.execution.Execution
import com.dimajix.flowman.model.BaseTarget
import com.dimajix.flowman.model.Target
import com.dimajix.flowman.spec.annotation.TargetType
import com.dimajix.flowman.spec.target.TargetSpec


case class HelloWorldTarget(
    instanceProperties:Target.Properties
) extends BaseTarget {
    /**
      * Abstract method which will perform the given task.
      *
      * @param execution
      */
    override def build(execution: Execution): Unit = {
        println("Hello world!")
    }
}



@TargetType(kind="hello-world")
class HelloWorldTargetSpec extends TargetSpec {
    override def instantiate(context: Context): Target = {
        HelloWorldTarget(
            instanceProperties(context)
        )
    }
}
