#!groovy

node {
        //checkout scm
        sh "ls"
        
        stage "Check for Pull Request"
        def isPullRequest = true
        sh "echo $env.BRANCH_NAME | tr '[:upper:]' '[:lower:]' > branchnamefile"
        def branchName=readFile('branchnamefile').trim()
        String comment = ""
        String targetBranch = ""
        try {
                sh "mv /tmp/targetFileGHPRB.properties `pwd`"
                sh "mv /tmp/commentFileGHPRB.properties `pwd`"
                comment = readFile("commentFileGHPRB.properties")
                targetBranch = readFile("targetFileGHPRB.properties")
        } catch (err) {
                isPullRequest = false
        }
        
        try {
                if(isPullRequest == true) {
                        if("$targetBranch" == "dev") { //If this is a pull request to dev
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
                            sh "git checkout -b MergeTestBranch origin/$env.BRANCH_NAME"
                            sh "git merge origin/dev"
                            
                            stage "Build Image"
                            sh ('sudo usermod -aG docker jenkins && groups')
                            def environment = docker.build "wine-spring-service:$branchName-$env.BUILD_ID" 
                            
                            stage "Unit tests"
                            
                            environment.inside {
                              stage 'Unit Tests'
                                  sh 'mvn install'

                              if (env.BRANCH_NAME == 'master') {
                                 //temporarily commented out bc it's annoying
                                  // stage 'Manual Gate'
                                  //     def userInput = input(
                                  //         id: 'userInput', message: 'Continue to next stage?', submitter: "GSA*Pipeline"

                                  //         // , parameters: [
                                  //         // [$class: 'TextParameterDefinition', defaultValue: 'uat1', description: 'Target', name: 'target']
                                  //         // ]
                                  //     ) 

                                  stage 'Functional Tests'
                                  //Nothing here yet
                              }

                              junit 'target/surefire-reports/TEST-dbDemo.PgBootApplicationTests.xml'
                           }
                             
                            stage "Push Images"
                            //push image - brianaslateradm/blueocean
                            sh "docker pull dockerhub-app-01.east1e.nonprod.dmz/brianaslateradm/blueocean"
                            sh "docker tag wine-spring-service:$branchName-$env.BUILD_ID dockerhub-app-01.east1e.nonprod.dmz/brianaslateradm/blueocean:$branchName-$env.BUILD_ID"
                            sh "docker push dockerhub-app-01.east1e.nonprod.dmz/brianaslateradm/blueocean:$branchName-$env.BUILD_ID"
                        } else if("$targetBranch" == "master") { //If this is a pull request to master
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
                            sh "git checkout -b MergeTestBranch origin/$env.BRANCH_NAME"
                            sh "git merge origin/master"
                            
                            stage "Deploy"
                                    if("$comment" ==~ ".*ok\\W+to\\W+test.*") {
                                        echo "I am ok to test."
                                        
                                    } else if("$comment" ==~ "deploy\\W+to\\W+minc\\W*") {
                                        echo "I am deploying to MinC."
                                        
                                    } else if("$comment" ==~ "deploy\\W+to\\W+prod\\W+like\\W*") {
                                        echo "I am ok to deploy to prod-like."
                                        
                                    } else if("$comment" ==~ "deploy\\W+to\\W+prod\\W*") {
                                        echo "I am ok to deploy to prod."
                                    }
                            
                        }
                        
                } else { //If this happens then this build is not the result of a pull request
                        echo "params.properties not found"
                        stage "Build Image"
                        sh ('sudo usermod -aG docker jenkins && groups')
                        sh "echo $branchName"
                        
                        stage "Unit tests"
                            environment.inside {
                              stage 'Unit Tests'
                                  sh 'mvn install'

                              if (env.BRANCH_NAME == 'master') {
                                 //temporarily commented out bc it's annoying
                                  // stage 'Manual Gate'
                                  //     def userInput = input(
                                  //         id: 'userInput', message: 'Continue to next stage?', submitter: "GSA*Pipeline"

                                  //         // , parameters: [
                                  //         // [$class: 'TextParameterDefinition', defaultValue: 'uat1', description: 'Target', name: 'target']
                                  //         // ]
                                  //     ) 

                                  stage 'Functional Tests'
                                  //Nothing here yet
                              }

                              junit 'target/surefire-reports/TEST-dbDemo.PgBootApplicationTests.xml'
                           }
                         
                        stage "Push Images" 
                        //push image - brianaslateradm/blueocean
                        sh "docker pull dockerhub-app-01.east1e.nonprod.dmz/brianaslateradm/blueocean"
                        sh "docker tag wine-spring-service:$branchName-$env.BUILD_ID dockerhub-app-01.east1e.nonprod.dmz/brianaslateradm/blueocean:$branchName-$env.BUILD_ID"
                        sh "docker push dockerhub-app-01.east1e.nonprod.dmz/brianaslateradm/blueocean:$branchName-$env.BUILD_ID"
                }
                
                
                } catch (err) {
            echo "Error occured"
            currentBuild.result = "FAILURE"
            throw err
        }
}



