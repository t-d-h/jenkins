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
    }
}