# PowerShell script to test the API
Write-Host "Starting application test..."

# Test if application is running
try {
    $response = Invoke-RestMethod -Uri "http://localhost:8080/api/customers" -Method GET
    Write-Host "Application is running. Customers response:"
    $response | ConvertTo-Json -Depth 3
} catch {
    Write-Host "Application is not running or not responding: $($_.Exception.Message)"
}

# Test creating a customer
try {
    $customer = @{
        nome = "Test Customer"
        cpf = "12345678901"
        endereco = "Test Address"
        telefone = "123456789"
        email = "test@example.com"
    }
    
    $json = $customer | ConvertTo-Json
    Write-Host "Sending customer data: $json"
    
    $response = Invoke-RestMethod -Uri "http://localhost:8080/api/customers" -Method POST -Body $json -ContentType "application/json"
    Write-Host "Customer created:"
    $response | ConvertTo-Json -Depth 3
} catch {
    Write-Host "Error creating customer: $($_.Exception.Message)"
}
