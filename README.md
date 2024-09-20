## Калькулятор отпускных
<img src="https://img.shields.io/github/actions/workflow/status/KepLer100500/Vacation-Pay-Calculator/main.yml?style=plastic&logo=spring&logoColor=green&label=Tests">

### Usage

#### For test:

````
curl -X GET "http://localhost:8989/api/v1/calculate?averageSalary=60000&vacationDays=7&startDate=2024-03-04&endDate=2024-03-10" -u "user:neoflex"
````
`
{"totalPay":8191.126279863481}
`
````
curl -X GET "http://localhost:8989/api/v1/calculate?averageSalary=60000&vacationDays=7" -u "user:neoflex"
````
`
{"totalPay":14334.470989761092}
`
#### For prod:

````
curl -X GET "http://kepler-developer.ru:8989/api/v1/calculate?averageSalary=60000&vacationDays=7&startDate=2024-03-04&endDate=2024-03-10" -u "user:neoflex"
````
`
{"totalPay":8191.126279863481}
`
````
curl -X GET "http://kepler-developer.ru:8989/api/v1/calculate?averageSalary=60000&vacationDays=7" -u "user:neoflex"
````
`
{"totalPay":14334.470989761092}
`
