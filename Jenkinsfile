pipeline {
    agent any 
    stages {
        stage('Complie Stage') {

            steps {
                withMaven(maven : 'maven_3_5_0') {
                    sh 'mvn clean complie'
                }
            }
        }
        stage('Testing Stage') { 
            steps {
                withMaven(maven : 'maven_3_5_0') {
                    sh 'mvn test'
                }
            }
        }
        stage('Deploy Stage') { 
            steps {
                withMaven(maven : 'maven_3_5_0') {
                    sh 'mvn deploy'
                }
            }
        }
    }
}
