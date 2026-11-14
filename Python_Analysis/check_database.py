import mysql.connector
from getpass import getpass

password = getpass("Enter MySQL root password: ")

try:
    connection = mysql.connector.connect(
        host="localhost",
        port=3306,
        user="root",
        password=password,
        database="water_supply"
    )

    print("\nDATABASE CONNECTION SUCCESSFUL!")

    cursor = connection.cursor()
    cursor.execute("SHOW TABLES")

    print("\nTables in water_supply:")
    for table in cursor.fetchall():
        print("-", table[0])

    cursor.close()
    connection.close()

except mysql.connector.Error as error:
    print("\nDATABASE CONNECTION FAILED!")
    print(error)
