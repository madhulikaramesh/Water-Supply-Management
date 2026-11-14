import mysql.connector
from getpass import getpass

password = getpass("Enter MySQL root password: ")

connection = mysql.connector.connect(
    host="localhost",
    user="root",
    password=password,
    database="water_supply"
)

cursor = connection.cursor()

tables = [
    "consumer",
    "water_connection",
    "meter_reading",
    "water_bill",
    "payment"
]

for table in tables:
    cursor.execute(f"SHOW CREATE TABLE {table}")
    result = cursor.fetchone()
    print("\n==========", table, "==========")
    print(result[1])

cursor.close()
connection.close()
