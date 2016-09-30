#!groovy

node {
    //sh "env | sort"
    
    // This block delets the .git repo if it exists.
    // If we don't, then we will only be able to make the "mergeTest" branch once.
    if(fileExists ('.git')) {
      sh "rm -rf .git"
      echo "file removed."
    }
    
    //Checks out the latest code, creates a local branch "MergeTestBranch" and tries to merge there
    checkout scm
    sh "git fetch origin"
    sh "git checkout -b MergeTestBranch origin/$env.BRANCH_NAME"
    sh "git merge origin/master"
    sh "cat README.md"
}
