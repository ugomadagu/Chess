#!groovy

node {
    sh "pwd"
    
    sh "mv /tmp/params.properties `pwd`"
    String contents = readFile("params.properties")
    //echo "$contents"
    
    if($contents ==~ ".*ok\\W+to\\W+test.*") {
        echo "I am working."
    }
}
