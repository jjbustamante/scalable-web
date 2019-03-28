#!/usr/bin/env groovy

pipeline {
    agent none
    options {
        timestamps()
    }
    stages {
        stage('Build') {
            steps {
                sh "mvn test"    
            }
        }
    }
}
