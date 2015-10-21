package sri.reactive2015.utils

import sri.mobile.ReactNative

object Device {

  val width = ReactNative.Dimensions.get("window").width.asInstanceOf[Double]

  val height = ReactNative.Dimensions.get("window").height.asInstanceOf[Double]

}
