import mysql.connector

# 建立与MySQL数据库的连接
mydb = mysql.connector.connect(
    host="192.168.133.130",
    user="root",
    password="hadoop",
    database="graduation"
)

# 从表中提取数据
mycursor = mydb.cursor()
mycursor.execute("SELECT * FROM all_data")
myresult = mycursor.fetchall()
for x in myresult:
    print(x)
