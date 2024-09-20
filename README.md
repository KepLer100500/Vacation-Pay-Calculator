curl -X GET "http://localhost:8989/api/v1/calculate?averageSalary=50000&vacationDays=14" -u "user:neoflex"

curl -G "http://localhost:8989/api/v1/calculate" -u "user:neoflex" \
--data-urlencode "averageSalary=50000" \
--data-urlencode "vacationDays=14"

curl -X GET "http://localhost:8989/api/v1/calculate?averageSalary=50000&vacationDays=14&startDate=2023-09-01&endDate=2023-09-15" -u "user:neoflex"

curl -G "http://localhost:8989/api/v1/calculate" -u "user:neoflex" \
--data-urlencode "averageSalary=50000" \
--data-urlencode "vacationDays=14" \
--data-urlencode "startDate=2023-09-01" \
--data-urlencode "endDate=2023-09-15"
