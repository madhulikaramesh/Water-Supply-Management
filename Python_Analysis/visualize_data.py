import mysql.connector
import pandas as pd
import matplotlib.pyplot as plt
from getpass import getpass
from pathlib import Path

password = getpass("Enter MySQL root password: ")

connection = mysql.connector.connect(
    host="localhost",
    port=3306,
    user="root",
    password=password,
    database="water_supply"
)

output_folder = Path(__file__).parent / "visualizations"
output_folder.mkdir(exist_ok=True)

def load_data(table):
    cursor = connection.cursor()
    cursor.execute(f"SELECT * FROM {table}")
    rows = cursor.fetchall()
    columns = [item[0] for item in cursor.description]
    cursor.close()
    return pd.DataFrame(rows, columns=columns)

# Load database tables
consumers = load_data("consumer")
payments = load_data("payment")
bills = load_data("water_bill")
readings = load_data("meter_reading")

# Chart 1: Consumers by Address
consumers["address"].value_counts().plot(
    kind="bar", title="Consumers by Address"
)
plt.xlabel("Address")
plt.ylabel("Number of Consumers")
plt.xticks(rotation=30)
plt.tight_layout()
plt.savefig(output_folder / "consumer_chart.png")
plt.close()

# Chart 2: Payments by Method
payments["payment_method"].value_counts().plot(
    kind="bar", title="Payments by Payment Method"
)
plt.xlabel("Payment Method")
plt.ylabel("Number of Payments")
plt.xticks(rotation=30)
plt.tight_layout()
plt.savefig(output_folder / "payment_chart.png")
plt.close()

# Chart 3: Water Bills by Status
bills["payment_status"].value_counts().plot(
    kind="pie", autopct="%1.1f%%",
    title="Water Bills by Payment Status"
)
plt.ylabel("")
plt.tight_layout()
plt.savefig(output_folder / "bill_status_chart.png")
plt.close()

# Chart 4: Water Consumption by Connection
readings["units_consumed"] = (
    pd.to_numeric(readings["current_reading"]) - pd.to_numeric(readings["previous_reading"])
)

readings.groupby("connection_id")["units_consumed"].sum().plot(
    kind="bar", title="Water Consumption by Connection"
)
plt.xlabel("Connection ID")
plt.ylabel("Units Consumed")
plt.tight_layout()
plt.savefig(output_folder / "water_consumption_chart.png")
plt.close()

connection.close()

print("\nALL 4 CHARTS CREATED SUCCESSFULLY!")
print("Saved location:", output_folder)