#!groovy

node {
    sh "pwd"
    
    //String fileContents = readFile('test.txt')
    //sh "echo $fileContents"
    
    sh "mv /tmp/params.properties `pwd`"
    String contents = readFile("params.properties")
    echo "$contents"
}
