#!groovy

node {
    //String fileContents = new File('/tmp/params.properties').text
    def fileContents = new File('${workspace}/params.properties').text 
    sh "echo $fileContents"
}
