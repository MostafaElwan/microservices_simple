db.createUser(
        {
            user: "melwan",
            pwd: "melwan123",
            roles: [
                {
                    role: "readWrite",
                    db: "customers_db"
                }
            ]
        }
);