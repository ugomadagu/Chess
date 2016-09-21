#!groovy

node {
    //String fileContents = new File('/tmp/params.properties').text
    //sh "echo $fileContents"
    sh "pwd"
    
    String fileContents = readFile('test.txt')
    sh "echo $fileContents"
}
