#!groovy

node {
    sh "env | sort"
    sh "git fetch origin"
    sh "git checkout -b MergeTest origin/MergeTest"
    sh "git merge master"
    sh "cat README.md"
}
