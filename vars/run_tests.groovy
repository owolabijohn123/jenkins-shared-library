// vars/run_tests.groovy
def call() {
    sh """
    docker run --rm \\
        -v \$(pwd):/app \\
        -w /app \\
        node:18 \\
        sh -c "npm install && npm test"
    """
}
