pipeline {
    agent any 
    stages {
        stage('Stage 1') {
            steps {
                echo 'Hello world!' 
                echo '2nd command'
            }
        }
        
        stage('Copy Package to test enviroment') {
            steps {
                sh '''scp /var/lib/jenkins/workspace/python* root@192.168.1.181:~'''
                echo '2nd command'
            }
        }

        stage('Install on testing node') {
            agent { label 'testing-env' }
            steps { 
                sh '''dpkg -i /root/python-webserver*'''
            }
        }

        stage('Testing on test enviroment') {
            agent { label 'testing-env' }
            steps {
                script {
                    def status = sh(returnStatus: true, script: "curl http://127.0.0.1/check > check.txt")
                    if (status != "OK") {
                    def output = readFile('check.txt').trim()
                    error 'Your application is not running, test failed'
                    }
                    sh 'rm check.txt'
                }
            }
        }
    }
}