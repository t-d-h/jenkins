pipeline {
    agent any
    stages {
        stage('Copy Package to testing node') {
            steps {
                sh '''scp /var/lib/jenkins/workspace/python* root@192.168.1.181:~'''
                echo 'Copied to Testing node'
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

        stage('Approve to deploy on prod') {
            steps {
                script {
                    echo 'Test tren moi truong test thanh cong'
                    input(message: "Deploy len production nhe?", ok: 'OK')
                }
            }
        }

        stage('Copy Package to prod node') {
            steps {
                sh '''scp /var/lib/jenkins/workspace/python* root@192.168.1.182:~'''
                echo 'Copied to prod node'
            }
        }
        
        stage('Install package on Production') {
            agent { label 'prod-env' }
            steps {
                sh '''dpkg -i /root/python-webserver*'''
            }
        }
        

        stage('Post check on Production environment') {
            agent { label 'prod-env' }
            steps {
                script {
                    def status = sh(returnStatus: true, script: "curl http://127.0.0.1/check > check.txt")
                    if (status != "OK") {                
                    def output = readFile('check.txt').trim()
                    error 'Your application is not running, Emergency'
                    }
                    sh 'rm check.txt'
                }
            }
        }
    }
}