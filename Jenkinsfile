#!groovy

node {
    
    sh "echo hello cuz1egr"
}

def merge() {
        
        //sh "env | sort"

        // This block deletes the .git repo if it exists.
        // If we don't do this, then we will only be able to make the "MergeTestBranch" branch once.
        if(fileExists ('.git')) {
          sh "rm -rf .git"
        }

        //Checks out the latest code, creates a local branch "MergeTestBranch" and tries to merge there
        // If merge is not successfull, git will complain and the job will fail
        checkout scm
        sh "git fetch origin"
        sh "git checkout -b MergeTestBranch origin/$env.BRANCH_NAME"
        sh "git merge origin/master"
    }
