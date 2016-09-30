#!groovy

node {
    //sh "env | sort"
    if(fileExists ('.git')) {
      sh "rm -rf .git"
      echo "file removed."
    }
    checkout scm
    sh "git fetch origin"
    sh "git checkout -b MergeTest16 origin/MergeTest"
    sh "git merge origin/master"
    sh "cat README.md"
}
