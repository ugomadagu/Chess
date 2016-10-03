#!groovy

node {
        //checkout scm
        sh "ls"
        
        stage "Check for Pull Request"
        def isPullRequest = true
        sh "echo $env.BRANCH_NAME | tr '[:upper:]' '[:lower:]' > branchnamefile"
        def branchName=readFile('branchnamefile').trim()
        
        try {
                sh "mv /tmp/params.properties `pwd`"
        } catch (err) {
                isPullRequest = false
        }
        echo "$isPullRequest"
        
        try {
                if(isPullRequest == true) {
                        if("$branchName" != "dev") { //If this is a pull request to dev
                            echo "pushing to dev"
                            stage "Local Merge"
                            // This block deletes the .git repo if it exists.
                            // If we don't do this, then we will only be able to make the "MergeTestBranch" branch once.
                            if(fileExists ('.git')) {
                                sh "rm -rf .git"
                            }    
    
                            //Checks out the latest code, creates a local branch "MergeTestBranch" and tries to merge there
                            // If merge is not successfull, git will complain and the job will fail
                            checkout scm
                            sh "git fetch origin"
                            sh "git checkout -b MergeTestBranch origin/$branchName"
                            sh "git merge origin/dev"
                            
                            stage "Build Image"
                            sh ('sudo usermod -aG docker jenkins && groups')
                            def environment = docker.build "wine-spring-service:$branchName-$env.BUILD_ID" 
                            
                            stage "Unit tests"
                            //Place holder
                             
                            stage "Push Images"
                            //push image - brianaslateradm/blueocean
                            //sh "docker pull dockerhub-app-01.east1e.nonprod.dmz/brianaslateradm/blueocean"
                            //sh "docker tag wine-spring-service:$branchName-$env.BUILD_ID dockerhub-app-01.east1e.nonprod.dmz/brianaslateradm/blueocean:$branchName-$env.BUILD_ID"
                            //sh "docker push dockerhub-app-01.east1e.nonprod.dmz/brianaslateradm/blueocean:$branchName-$env.BUILD_ID"
                        } else { //If this is a pull request to master
                            echo "pushing to master"
                            stage "Local Merge"
                            // This block deletes the .git repo if it exists.
                            // If we don't do this, then we will only be able to make the "MergeTestBranch" branch once.
                            if(fileExists ('.git')) {
                                sh "rm -rf .git"
                            }    
    
                            //Checks out the latest code, creates a local branch "MergeTestBranch" and tries to merge there
                            // If merge is not successfull, git will complain and the job will fail
                            checkout scm
                            sh "git fetch origin"
                            sh "git checkout -b MergeTestBranch origin/$branchName"
                            sh "git merge origin/dev"
                            
                            stage "Deploy"
                                    String contents = readFile("params.properties")
                                    
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
                        
                } else { //If this happens then this build is not the result of a pull request
                        echo "params.properties not found"
                        stage "Build Image"
                        sh ('sudo usermod -aG docker jenkins && groups')
                        sh "echo $branchName"
                        
                        stage "Unit tests"
                        //Place holder
                         
                        stage "Push Images" 
                        //push image - brianaslateradm/blueocean
                        //sh "docker pull dockerhub-app-01.east1e.nonprod.dmz/brianaslateradm/blueocean"
                        //sh "docker tag wine-spring-service:$branchName-$env.BUILD_ID dockerhub-app-01.east1e.nonprod.dmz/brianaslateradm/blueocean:$branchName-$env.BUILD_ID"
                        //sh "docker push dockerhub-app-01.east1e.nonprod.dmz/brianaslateradm/blueocean:$branchName-$env.BUILD_ID"
                }
                
                
                } catch (err) {
            echo "Error occured"
            currentBuild.result = "FAILURE"
            throw err
        }
}


