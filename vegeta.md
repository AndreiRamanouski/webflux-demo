# Vegeta Load Testing

vegeta --help

`echo "GET http://localhost:8888/assets" | vegeta attack -workers=4 -max-workers=10 -duration=30s | tee results.bin | vegeta report`

echo "GET http://localhost:8080/users/1" | vegeta attack -workers=4 -max-workers=10 -duration=30s | tee results.bin | vegeta report
