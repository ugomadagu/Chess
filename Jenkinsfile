#!groovy

node {
    sh "env | sort"
    sh "rm -rf .git"
    checkout scm
    sh "git fetch origin"
    //sh "git checkout -b MergeTest$env.BUILD_ID origin/MergeTest"
    sh "git checkout -b MergeTest16 origin/MergeTest"
    sh "git merge origin/master"
    sh "cat README.md"
}
