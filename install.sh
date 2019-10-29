pushd dl

gradle build

popd

pushd bl

gradle build

popd

pushd pl

gradle build

popd

pushd pl/testcases

javac -cp ../build/libs/pl.jar:../../dl/build/libs/dl.jar:../../bl/build/libs/bl.jar:../libs/*: AuthorFrameTestCase.java

popd
