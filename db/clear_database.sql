-- 🚨 User Table (1 User ➡️ Many Reports)
# Main Table: user
CREATE TABLE users (
                       id VARCHAR(50) PRIMARY KEY,
                       name VARCHAR(100) NOT NULL,
                       email VARCHAR(150) NOT NULL UNIQUE,
                       phone_number VARCHAR(255) NOT NULL,
                       password_hash VARCHAR(255) NOT NULL,
                       role ENUM('user', 'admin') DEFAULT 'user',
                       status ENUM('active', 'inactive') DEFAULT 'active',
                       report_count INT DEFAULT 0,
                       last_login DATETIME DEFAULT NULL,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 🧠 Main Table: Missing Person
CREATE TABLE missing_person (
                                id VARCHAR(50) PRIMARY KEY,                                -- Custom ID like 'MIS-2025-00001'
                                name VARCHAR(100) NOT NULL,                                -- Full name of the missing person
                                age INT NOT NULL,                                          -- Age
                                gender ENUM('Male', 'Female', 'Other') NOT NULL,           -- Gender options
                                photo_url VARCHAR(255),                                    -- Photo path or URL

                                location VARCHAR(255),                                     -- Last seen location
                                clothing TEXT,                                             -- Clothes worn when last seen
                                circumstances TEXT,                                        -- Details about how the person went missing

                                status ENUM('missing', 'found') DEFAULT 'missing', -- Current person status

                                missing_date DATE,                    -- Date the person was reported missing
                                missing_time TIME,                                         -- Time they went missing

                                reward VARCHAR(50) DEFAULT 'No Reward',                    -- Optional reward

                                height VARCHAR(20),                                        -- Height info (ex: '5ft 7in' or '170cm')
                                weight VARCHAR(20),                                        -- Weight info (ex: '60kg' or '132lbs')
                                hair_color VARCHAR(50),                                    -- Hair color
                                eye_color VARCHAR(50),                                     -- Eye color
                                distinguishing_features TEXT,                              -- Scars, tattoos, etc.

                                reporter_id VARCHAR(50),                                   -- Reporter FK (who reported this)
                                relation VARCHAR(50),                          -- Relation to missing person

                                accepted_term_policy ENUM('yes', 'no') DEFAULT 'no',
                                confirmed_details ENUM('yes', 'no') DEFAULT 'no',

                                created_at DATETIME DEFAULT CURRENT_TIMESTAMP,             -- When this record was created
                                updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- Auto-update
                                FOREIGN KEY (reporter_id) REFERENCES user(id) ON DELETE CASCADE

);

# Sub table: last_seen_details
CREATE TABLE last_seen_details (
                                   missing_person_id VARCHAR(50),
                                   date DATE,
                                   location VARCHAR(255),
                                   FOREIGN KEY (missing_person_id) REFERENCES missing_person(id) ON
                                       DELETE CASCADE
);


