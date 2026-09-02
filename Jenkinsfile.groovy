pipeline {
    agent any

    tools {
        jdk 'jdk21'
    }

    options {
        buildDiscarder(logRotator(numToKeepStr: '10'))
        timestamps()
        disableConcurrentBuilds()
    }

    stages {
        stage('Check JDK') {
            steps {
                bat 'java -version'
            }
        }

        stage('Build') {
            steps {
                bat 'gradlew.bat clean build -x test --no-daemon'
            }
        }

        stage('Test') {
            steps {
                bat 'gradlew.bat test --no-daemon'
            }
            post {
                always {
                    junit testResults: '**/build/test-results/test/*.xml', allowEmptyResults: true
                }
            }
        }

        stage('Allure Report') {
            steps {
                bat 'gradlew.bat allureReport --no-daemon'
            }
            post {
                always {
                    allure includeProperties: false,
                            jdk: '',
                            results: [[path: 'build/allure-results']]
                }
            }
        }
    }

    post {
        success {
            echo 'Build finalizado com sucesso!'
        }
        failure {
            echo 'Build falhou. Verifique os logs.'
        }
        always {
            cleanWs()
        }
    }
}