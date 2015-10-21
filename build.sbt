enablePlugins(ScalaJSPlugin)

name := "sri-reactive2015"

scalaVersion := "2.11.7"

val sriVersion = "0.2.0-SNAPSHOT"

libraryDependencies ++= Seq(
  "com.github.chandu0101.sri" %%% "mobile" % sriVersion,
  "com.github.chandu0101.sri" %%% "relay" % sriVersion
)


val fullOptMobile = Def.taskKey[File]("Generate mobile output file")

artifactPath in Compile in fullOptMobile :=
baseDirectory.value / "index.ios.js"

fullOptMobile in Compile := {
val outFile = (artifactPath in Compile in fullOptMobile).value

val loaderFile = (resourceDirectory in Compile).value / "loader.js"

IO.copyFile(loaderFile, outFile)

val fullOutputCode = IO.read((fullOptJS in Compile).value.data)

IO.append(outFile, fullOutputCode)

val launcher = (scalaJSLauncher in Compile).value.data.content
IO.append(outFile, launcher)

IO.copyFile(outFile,baseDirectory.value / "index.android.js")
outFile
}
    

