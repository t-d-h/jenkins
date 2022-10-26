pipeline {
    agent any 
    stages {
        stage('Trigger build package') {
            steps {
                withMaven(maven : 'maven_3_5_0') {
                    echo 'build package python'
                }
            }
        }
        stage('Deploy on test environment') { 
            steps {
                withMaven(maven : 'maven_3_5_0') {
                    echo 'install on dev server'
                }
            }
        }
        stage('Check on dev environment') { 
            steps {
                withMaven(maven : 'maven_3_5_0') {
                    echo 'success or not'
                }
            }
        }
        stage('Deploy on Product') { 
            steps {
                withMaven(maven : 'maven_3_5_0') {
                    echo 'Install on Prod'
                }
            }
        }
        stage('Last check') { 
            steps {
                withMaven(maven : 'maven_3_5_0') {
                    echo 'Done'
                }
            }
        }        
    }
}
