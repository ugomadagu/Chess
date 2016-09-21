#!groovy

node {
    sh "pwd"
    
    sh "mv /tmp/params.properties `pwd`"
    String contents = readFile("params.properties")
    //echo "$contents"
    
    if("$contents" ==~ ".*ok\\W+to\\W+test.*") {
        echo "I am ok to test."
    } else if("$contents" ==~ "deploy\\W+to\\W+minc\\W*") {
        echo "I am deploying to MinC."
    } else if("$contents" ==~ "deploy\\W+to\\W+prod\\W+like\\W*") {
        echo "I am ok to deploy to prod-like."
    } else if("$contents" ==~ "deploy\\W+to\\W+prod\\W*") {
        echo "I am ok to deploy to prod."
    }
}
