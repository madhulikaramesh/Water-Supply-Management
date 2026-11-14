import mysql.connector
import pandas as pd
from getpass import getpass

password = getpass("Enter MySQL root password: ")

connection = mysql.connector.connect(
    host="localhost",
    port=3306,
    user="root",
    password=password,
    database="water_supply"
)

tables = [
    "consumer",
    "meter_reading",
    "payment",
    "water_bill",
    "water_connection"
]

for table in tables:
    df = pd.read_sql(f"SELECT * FROM {table}", connection)

    print("\n" + "=" * 50)
    print("TABLE:", table.upper())
    print("=" * 50)
    print(df.to_string(index=False))
    print("\nRows and Columns:", df.shape)

connection.close()
