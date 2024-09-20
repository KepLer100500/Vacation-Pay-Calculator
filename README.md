For test:
curl -X GET "http://localhost:8989/api/v1/calculate?averageSalary=60000&vacationDays=7&startDate=2024-03-04&endDate=2024-03-10" -u "user:neoflex"
curl -X GET "http://localhost:8989/api/v1/calculate?averageSalary=60000&vacationDays=7" -u "user:neoflex"

For prod:

curl -X GET "http://kepler-developer.ru:8989/api/v1/calculate?averageSalary=60000&vacationDays=7&startDate=2024-03-04&endDate=2024-03-10" -u "user:neoflex"
curl -X GET "http://kepler-developer.ru:8989/api/v1/calculate?averageSalary=60000&vacationDays=7" -u "user:neoflex"
