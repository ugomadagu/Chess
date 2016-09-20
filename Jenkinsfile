#!groovy

node {
    //String fileContents = new File('/tmp/params.properties').text
    String fileContents = new File('${workspace}/params.properties').text 
    sh "echo $fileContents"
}
