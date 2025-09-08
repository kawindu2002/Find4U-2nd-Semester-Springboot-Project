# Main Table: missing_persons
CREATE TABLE missing_person (



#     id,name,age,gender,height,weight,hair_color,eye_color,distinguishing_features,photo_url
#     missing_date,time,location,clothing,circumstances
#     reporter_name,phone_number,email,relation,reward
#     created_at,updated_at


                                 id VARCHAR(50) PRIMARY KEY,              -- e.g. '1', '2', 'MIS-2025-00001'
                                 name VARCHAR(100) NOT NULL,              -- Person's full name
                                 age INT NOT NULL,                        -- Age of the person
                                 gender VARCHAR(10) NOT NULL,             -- Gender: Male/Female/Other
                                 description TEXT,                        -- Optional detailed description
                                 photo_url VARCHAR(255),                  -- URL/path to the uploaded photo
                                 status ENUM('missing', 'found', 'unconfirmed') DEFAULT 'missing',  -- Current status
                                 missing_date DATE DEFAULT CURRENT_DATE,   -- Date report was made
                                 reward VARCHAR(50) DEFAULT 'No Reward', -- Reward offered (optional)

                                 height VARCHAR(20),
                                 weight VARCHAR(20),
                                 hair_color VARCHAR(50),
                                 eye_color VARCHAR(50),
                                 distinguishing_features TEXT,

                                 reporter_name VARCHAR(100),
                                 relation VARCHAR(50),
                                 phone VARCHAR(20),
                                 email VARCHAR(100),
                                 created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                                 updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

# VARCHAR(50) is enough for simple strings like:
# 'Rs. 10,000', 'Negotiable', 'No Reward', etc.


# Table - reporter
CREATE TABLE reporter (
                          id VARCHAR(50) PRIMARY KEY,
                          name VARCHAR(100) NOT NULL,
                          phone_number VARCHAR(20),
                          address TEXT,
                          email VARCHAR(100),
                          relation VARCHAR(50)
);


# Sub table: last_seen_details
CREATE TABLE last_seen_details (
                                   missing_person_id VARCHAR(50),
                                   date DATE,
                                   location VARCHAR(255),
                                   FOREIGN KEY (missing_person_id) REFERENCES missing_person(id) ON
                                       DELETE CASCADE
);
#
# # Sub table: physical_attributes
# CREATE TABLE physical_attributes (
#                                      missing_person_id VARCHAR(50),
#                                      height VARCHAR(20),
#                                      weight VARCHAR(20),
#                                      hair_color VARCHAR(50),
#                                      eye_color VARCHAR(50),
#                                      distinguishing_features TEXT,
#                                      FOREIGN KEY (missing_person_id) REFERENCES missing_person(id) ON
#                                          DELETE CASCADE
# );
#
# # Sub table: contact_info
# CREATE TABLE contact_info (
#                               missing_person_id VARCHAR(50),
#                               reporter_name VARCHAR(100),
#                               relation VARCHAR(50),
#                               phone VARCHAR(20),
#                               email VARCHAR(100),
#                               FOREIGN KEY (missing_person_id) REFERENCES missing_person(id) ON
#                                   DELETE CASCADE
# );

# Sub table: report_updates (0 or many updates per person)
CREATE TABLE report_updates (
                                id INT AUTO_INCREMENT PRIMARY KEY,
                                missing_person_id VARCHAR(50),
                                date DATE,
                                content TEXT,
                                source VARCHAR(100),
                                FOREIGN KEY (missing_person_id) REFERENCES missing_person(id) ON
                                    DELETE CASCADE
);

# Main Table: users
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

# Main Table: activity_logs
CREATE TABLE activity_logs (
                               id INT AUTO_INCREMENT PRIMARY KEY,
                               user_id INT NOT NULL,
                               case_id INT NOT NULL,
                               type ENUM(
                                   'case_reported',
                                   'case_updated',
                                   'user_registered',
                                   'animal_reported',
                                   'tip_submitted'
                                   ) NOT NULL,
                               content TEXT NOT NULL,
                               date DATETIME DEFAULT CURRENT_TIMESTAMP,
                               FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE,
                               FOREIGN KEY (case_id) REFERENCES missing_person(id) ON DELETE CASCADE);


# Main Table: case_status_history
# To track every status change (who changed it, when):
CREATE TABLE case_status_history (
                                     id INT AUTO_INCREMENT PRIMARY KEY,
                                     missing_person_id VARCHAR(50),
                                     old_status ENUM('pending', 'approved', 'rejected', 'found'),
                                     new_status ENUM('pending', 'approved', 'rejected', 'found'),
                                     changed_by INT, -- admin user id
                                     changed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                     FOREIGN KEY (missing_person_id) REFERENCES missing_person(id),
                                     FOREIGN KEY (changed_by) REFERENCES users(id)
);


# Main Table: case_reports - This works only when state is found
CREATE TABLE case_reports (
                              id INT AUTO_INCREMENT PRIMARY KEY,
                              -- type ENUM('person', 'animal') NOT NULL,
                              status ENUM('pending', 'approved', 'found', 'rejected') DEFAULT 'pending',
                              reported_by INT, -- FK to users
                              report_date DATE,
                              found_date DATE,
                              -- Resolution Analysis
                              resolution_time_days INT, -- auto-calculate when marked 'found'
                              resolution_speed ENUM('within_24h', '1_7_days', '1_4_weeks',
                                  '1_3_months', '3_plus_months'),
                              -- Location
                              location VARCHAR(100),
                              latitude DECIMAL(10,7),
                              longitude DECIMAL(10,7),
                              FOREIGN KEY (reported_by) REFERENCES users(id)
);

# This supports engagement = COUNT(tips) for the chart tips submitted.
CREATE TABLE tips (
                      id INT AUTO_INCREMENT PRIMARY KEY,
                      case_id INT,
                      user_id INT,
                      message TEXT,
                      submitted_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                      FOREIGN KEY (case_id) REFERENCES case_reports(id),
                      FOREIGN KEY (user_id) REFERENCES users(id)
);

# app_settings table
CREATE TABLE app_settings (
                              id INT AUTO_INCREMENT PRIMARY KEY,
                              category ENUM('general', 'notifications', 'moderation') NOT NULL,
                              key_name VARCHAR(100) NOT NULL,
                              value TEXT,
                              updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                              UNIQUE(category, key_name)
);


# success_stories table
CREATE TABLE success_stories (
                                 id INT PRIMARY KEY AUTO_INCREMENT,
                                 name VARCHAR(100),
                                 age INT,
                                 image_url TEXT,
                                 location VARCHAR(100),
                                 missing_days INT,
                                 story TEXT,
                                 status ENUM('found', 'unresolved') DEFAULT 'found',
                                 created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                 updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE
                                             CURRENT_TIMESTAMP
);

# testimonials table
CREATE TABLE testimonials (
                              id INT PRIMARY KEY AUTO_INCREMENT,
                              name VARCHAR(100),
                              relationship VARCHAR(100),
                              initials VARCHAR(10),
                              quote TEXT,
                              story_id INT,
                              created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                              FOREIGN KEY (story_id) REFERENCES success_stories(id)
);

# transactions table
CREATE TABLE transactions (
                              id VARCHAR(64) PRIMARY KEY,                -- Unique transaction ID
                              user_id VARCHAR(64) NOT NULL,              -- FK: User who made payment
                              date DATETIME DEFAULT CURRENT_TIMESTAMP,   -- When the payment happened
                              amount DECIMAL(10, 2) NOT NULL,            -- Money paid (e.g. 100.00)
                              plan ENUM('Basic', 'Standard', 'Premium') NOT NULL,       -- Plan type
                              status ENUM('completed', 'pending', 'failed') DEFAULT 'pending',       -- PersonStatus
                              missing_person_id VARCHAR(64),      -- FK (optional): linked case
                              FOREIGN KEY (user_id) REFERENCES users(id),
                              FOREIGN KEY (missing_person_id) REFERENCES missing_person(id)
);


#
# ⚡ Example Use Case Flow
# 1. User Saves a Case:
# Insert into saved_cases
#
# Insert into activity_log (type='save', description='Saved case X')
#
#     2. User Submits a Tip:
# Insert into tips
#
# Insert into activity_log (type='tip', description='I saw this person at...')
#
#     3. User Follows a Case:
# Insert into follows
#
# Insert into activity_log (type='follow', description='Started following case')

# follows table
CREATE TABLE follows (
                         id INT PRIMARY KEY AUTO_INCREMENT,
                         user_id INT NOT NULL,
                         case_id INT NOT NULL,
                         followed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
                         FOREIGN KEY (case_id) REFERENCES missing_person(id) ON DELETE CASCADE
);

# tips table
CREATE TABLE tips (
                      id INT PRIMARY KEY AUTO_INCREMENT,
                      user_id INT NOT NULL,
                      case_id INT NOT NULL,
                      message TEXT NOT NULL,
                      submitted_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                      FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
                      FOREIGN KEY (case_id) REFERENCES missing_person(id) ON DELETE CASCADE
);

# saved_cases table
CREATE TABLE saved_cases (
                             id INT PRIMARY KEY AUTO_INCREMENT,
                             user_id INT NOT NULL,
                             case_id INT NOT NULL,
                             saved_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                             FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
                             FOREIGN KEY (case_id) REFERENCES missing_person(id) ON DELETE CASCADE
);


# ======================================================================================================================

# 🌟 Suggested Custom ID Patterns (Table-wise)

# 🧑‍🦱 missing_person → MIS-2025-001
# Part	Example	Meaning
# MIS	Fixed prefix	Missing Person
# 2025	Current Year	Helps separate yearly entries
# 001	Running number	Unique per year
#
# 💡 Use this as id and case_number.
#
# 📍 last_seen_details → No need for custom ID (linked to missing_person)
# Just use missing_person_id to reference. No separate ID needed.
#
# 🧬 physical_attributes → No custom ID needed
# Just FK. Keep it light and fast.
#
# ☎️ contact_info → No ID needed
# Each missing person only has one contact. Use missing_person_id as the FK.
#
# 📝 report_updates → UPD-<missingId>-<001>
# Example	Meaning
# UPD-MIS-2025-002-001	1st update of case MIS-2025-002
# UPD-MIS-2025-002-002	2nd update of same case
#
# 💡 Optional if you want friendly reference in emails/UI logs.
#
# 👤 users → USR-2025-001
# Part	Example
# USR	User
# 2025	Year joined
# 001	Running
#
# 💡 Useful if you expose user ID in UI or export CSVs.
#
# 🧠 activity_logs → LOG-2025-001
# Example	Meaning
# LOG-2025-055	55th log event in 2025
#
# Internally can still use INT AUTO_INCREMENT, this ID can be generated for frontend display.
#
# 🔁 case_status_history → STA-MIS-2025-001-001
# Example	Meaning
# STA-MIS-2025-001-002	2nd status update of missing case MIS-2025-001
#
# Optional but powerful for tracking change history.
#
# 📄 case_reports → REP-2025-001
# Example	Meaning
# REP-2025-002	Report ID
# REP-MIS-2025-001	(Optional) include MIS tag
#
# 💡 Tie this with custom ID MIS-2025-001 for linking missing case to report.
#
# 💬 tips → TIP-2025-001
# Example	Meaning
# TIP-2025-102	102nd tip this year
#
# Useful for tracking total community engagement.
#
# ⚙️ app_settings → No ID needed
# Key-value table — just keep it lean.
#
# 🌈 success_stories → SUC-2025-001
# Example	Meaning
# SUC-2025-004	4th story in 2025
#
# Nice for public-facing pages.
#
# ❤️ testimonials → TES-2025-001
# Example	Meaning
# TES-2025-018	18th testimonial
#
# Good for building social proof!
#
# 💸 transactions → TXN-<timestamp>-<userId>
# Example	Meaning
# TXN-20250716-0001	Payment from user 1 on 2025-07-16
# TXN-20250716-0001-100	Add case_id if needed
#
# 💡 Or just use UUID if you want 100% uniqueness + safety
#
# 🌟 follows, saved_cases → No ID needed
# They just track user-case relationships. ID is for internal use.
#
# ⚒ How to Auto-Generate Custom IDs in Java (Recap)

# String prefix = "MIS";
# String year = String.valueOf(LocalDate.now().getYear());
#
# // Count existing records (or get MAX)
# String sql = "SELECT COUNT(*) FROM missing_person WHERE id LIKE ?";
# PreparedStatement ps = conn.prepareStatement(sql);
# ps.setString(1, prefix + "-" + year + "-%");
#
# ResultSet rs = ps.executeQuery();
# int count = rs.next() ? rs.getInt(1) + 1 : 1;
#
# String customId = String.format("%s-%s-%03d", prefix, year, count);
# 💥 Final Table: All Custom ID Patterns
# Table	Custom ID Pattern
# missing_person	MIS-2025-001
# users	USR-2025-001
# case_reports	REP-2025-001
# report_updates	UPD-MIS-2025-001-001
# tips	TIP-2025-001
# success_stories	SUC-2025-001
# testimonials	TES-2025-001
# transactions	TXN-20250716-0001
# activity_logs	LOG-2025-001
# case_status_history	STA-MIS-2025-001-001


# 🧠 1. What is ObjectMapper?

# ObjectMapper is a Jackson class in Java that helps you:
# 🛠️ Convert JSON → Java object
# 🛠️ Convert Java object → JSON
# It’s part of com.fasterxml.jackson.databind.
#
# 🎯 2. Sample JSON (your case):

# {
#   "id": "NYC-23-45678",
#   "name": "Ayesha Fernando",
#   "age": 27,
#   "gender": "Female",
#   "description": "Last seen near Galle Face, wearing a red jacket and black jeans.",
#   "photo_url": "/photos/ayesha.png",
#   "status": "missing",
#   "report_date": "2025-07-16",
#   "case_number": "CASE-AY-001",
#   "reward": "No Reward"
# }

# 🧱 3. Create a Java class (MissingPerson.java)

# package com.find4u.model;
#
# public class MissingPerson {
#     private String id;
#     private String name;
#     private int age;
#     private String gender;
#     private String description;
#     private String photo_url;
#     private String status;
#     private String report_date;
#     private String case_number;
#     private String reward;
#
#     // 👉 Must-have: Getters & Setters
#     // 👉 Optional: Constructor, toString()
#
#     // Add these easily using your IDE or Lombok if you want
#     public String getId() { return id; }
#     public void setId(String id) { this.id = id; }
#
#     // Add other getters/setters here too...
#
#     @Override
#     public String toString() {
#         return "MissingPerson{" +
#                "id='" + id + '\'' +
#                ", name='" + name + '\'' +
#                ", age=" + age +
#                ", gender='" + gender + '\'' +
#                ", description='" + description + '\'' +
#                ", photo_url='" + photo_url + '\'' +
#                ", status='" + status + '\'' +
#                ", report_date='" + report_date + '\'' +
#                ", case_number='" + case_number + '\'' +
#                ", reward='" + reward + '\'' +
#                '}';
#     }
# }

# 🚀 4. Parse JSON into Java Object

# import com.fasterxml.jackson.databind.ObjectMapper;
# import com.find4u.model.MissingPerson;
#
# public class JsonParserExample {
#     public static void main(String[] args) {
#         try {
#             String json = "{\n" +
#                           "  \"id\": \"NYC-23-45678\",\n" +
#                           "  \"name\": \"Ayesha Fernando\",\n" +
#                           "  \"age\": 27,\n" +
#                           "  \"gender\": \"Female\",\n" +
#                           "  \"description\": \"Last seen near Galle Face, wearing a red jacket and black jeans.\",\n" +
#                           "  \"photo_url\": \"/photos/ayesha.png\",\n" +
#                           "  \"status\": \"missing\",\n" +
#                           "  \"report_date\": \"2025-07-16\",\n" +
#                           "  \"case_number\": \"CASE-AY-001\",\n" +
#                           "  \"reward\": \"No Reward\"\n" +
#                           "}";
#
#             ObjectMapper mapper = new ObjectMapper();
#             MissingPerson person = mapper.readValue(json, MissingPerson.class);
#
#             System.out.println("✅ Parsed Java object: ");
#             System.out.println(person);
#         } catch (Exception e) {
#             e.printStackTrace();
#         }
#     }
# }

# 🧪 5. Output

# ✅ Parsed Java object:
# MissingPerson{id='NYC-23-45678', name='Ayesha Fernando', age=27, ... }

# ✅ Dependencies (If you use Maven)

# <dependency>
#   <groupId>com.fasterxml.jackson.core</groupId>
#   <artifactId>jackson-databind</artifactId>
#   <version>2.15.0</version>
# </dependency>

# ⚠️ Tips

# ObjectMapper only works properly if all field names match exactly.
# If JSON keys are different from Java field names, use @JsonProperty("json_name").
# Dates can be parsed as String or better: LocalDate with a custom date format.


# 🧠 Where do we parse JSON in Spring Boot?
# ✅ Answer: Usually in the Controller Layer
# Why? Because:

# The Controller layer is the entry point for incoming HTTP requests (like POST/GET from frontend).
# Spring Boot (thanks to Jackson behind the scenes) automatically converts incoming JSON → Java object using @RequestBody.
# 🔥 Let's break it down like a pro:

# 📦 1. Controller Layer – JSON is parsed here

# @RestController
# @RequestMapping("/api/missing")
# public class MissingPersonController {
#
#     @PostMapping("/report")
#     public ResponseEntity<String> reportMissing(@RequestBody MissingPerson person) {
#         // 💡 At this point, Spring has already used ObjectMapper internally
#         // to convert JSON → MissingPerson object
#
#         System.out.println("🧍 Person reported: " + person);
#         return ResponseEntity.ok("✅ Missing person reported!");
#     }
# }
# 👉 So you don't even need to manually use ObjectMapper in most cases. Spring handles it for you!


# 🧬 2. Service Layer – Process business logic

# Once the object is parsed, it gets passed to the service:
# @Service
# public class MissingPersonService {
#     public void saveReport(MissingPerson person) {
#         // validate, log, enrich, or prepare data
#     }
# }

# 🗃️ 3. Repository Layer – Save to DB

# @Repository
# public interface MissingPersonRepository extends JpaRepository<MissingPerson, String> {
#     // Auto CRUD methods here
# }

# 💡 TL;DR Table:

# Layer	Responsibility	JSON parsing?
# Controller	Handle requests, parse JSON via @RequestBody	✅ YES
# Service	Business logic	❌ Already parsed
# Repository	DB CRUD	❌ Pure data access


# [Frontend sends JSON]
#         ↓
# [Spring Controller gets @RequestBody]
#         ↓
# [Spring Boot uses Jackson's ObjectMapper]
#         ↓
# [JSON → Java Object (MissingPerson)]
#         ↓
# [You handle it like a normal object 🧍]

# 🧠 Bonus: When to use java.sql.Date?

# Use java.sql.Date only inside repository layer or when directly mapping JDBC rows to Java objects (like with plain ResultSet).
#
# But in DTOs or Entities, stick to java.util.Date or java.time.LocalDate.




# ======================================================================================================================

# FAQ
#
# CREATE TABLE faq (
#     id INT AUTO_INCREMENT PRIMARY KEY,
#     question TEXT NOT NULL,
#     answer TEXT NOT NULL,
# );
#
# -- Insert the FAQ data
# INSERT INTO faq (question, answer) VALUES
# ('What is Find4U.com?', 'Find4U.com is a public service platform designed to help locate missing persons in Sri Lanka. It allows users to submit, search, and share reports while connecting with communities to increase the chances of recovery.'),
# ('Is this service free?', 'Yes, the core features such as posting and searching for missing people are free. However, paid features like promoted listings are not available for free.'),
# ('Who can use this platform?', 'Anyone in Sri Lanka or abroad can use this platform to report a missing person or pet, or to help identify and locate them.'),
# ('How do I post a missing person?', 'You can click the ''Report Missing'' button on the homepage, fill in all required details (such as name, last seen location, contact info, and photo), and submit it for moderation.'),
# ('How long will my post be visible?', 'Free posts only remain visible for 2 days. Paid posts can stay visible longer, based on the selected package.'),
# ('Can I update my report later?', 'Yes, you can log in to your account and edit or update your report at any time.'),
# ('What information should I include in a report?', 'Provide clear photos, name, age, physical description, last known location, date and time of disappearance, any health conditions, and contact details.'),
# ('How are reports verified?', 'All submitted reports go through a manual review process by our moderation team.'),
# ('Why was my post not approved?', 'Posts may be rejected if they contain false information, offensive language, spam, or violate our community guidelines.'),
# ('Is my personal information safe?', 'Yes. We only display necessary contact information and strictly follow our Privacy Policy to protect user data.'),
# ('Do I need to create an account?', 'Yes, you need an account to post or manage reports.'),
# ('I forgot my password. What do I do?', 'Click the ''Forgot Password'' link on the login page and follow the instructions to reset your password via email.'),
# ('What are promoted listings?', 'Promoted listings appear at the top of search results and homepage sections, increasing their visibility.'),
# ('How do I contact the Find4U.com team?', 'You can reach us via the Contact Us form or email us at support@find4u.com. We aim to respond within 24 – 48 hours.'),
# ('What if I found a missing person?', 'Please contact the owner directly using the provided contact details.');

# ==============================================================================================================================

# PLANS
#
# CREATE TABLE plan (
#                       id INT PRIMARY KEY AUTO_INCREMENT,
#                       plan_key VARCHAR(50) UNIQUE NOT NULL,
#                       price VARCHAR(20) NOT NULL,
#                       duration VARCHAR(50) NOT NULL,
#                       processing_fee DECIMAL(10,2) DEFAULT 0.00
# );
#
# PLAN_FEATURES
#
# CREATE TABLE plan_feature (
#                               id INT PRIMARY KEY AUTO_INCREMENT,
#                               plan_id INT NOT NULL,
#                               feature VARCHAR(255) NOT NULL,
#                               FOREIGN KEY (plan_id) REFERENCES plan(id) ON DELETE CASCADE,
#                               INDEX idx_plan_id (plan_id)
# );
#
# -- Insert plans
# INSERT INTO plan (plan_key, price, duration, processing_fee) VALUES
#                                                                  ('basic', 'Free', '2 days', 0.00),
#                                                                  ('standard','Rs.100.00', '7 days', 0.00),
#                                                                  ('premium', 'Rs.350.00', '30 days', 0.00);
#
# -- Insert features for Basic Plan
# INSERT INTO plan_feature (plan_id, feature) VALUES
#                                                 (1, 'Standard listing visibility'),
#                                                 (1, 'Basic search visibility'),
#                                                 (1, 'Email notifications'),
#                                                 (1, 'Report updates'),
#                                                 (1, 'Community sharing');
#
# -- Insert features for Standard Plan
# INSERT INTO plan_feature (plan_id, feature) VALUES
#                                                 (2, 'Enhanced listing visibility'),
#                                                 (2, 'Extended 15-day visibility'),
#                                                 (2, 'Increased search priority'),
#                                                 (2, 'Email notifications'),
#                                                 (2, 'Detailed report updates'),
#                                                 (2, 'Community sharing'),
#                                                 (2, 'Social media promotion');
#
# -- Insert features for Premium Plan
# INSERT INTO plan_feature (plan_id, feature) VALUES
#                                                 (3, 'Premium listing visibility'),
#                                                 (3, 'Maximum 30-day visibility'),
#                                                 (3, 'Featured on interactive map'),
#                                                 (3, 'Top search ranking'),
#                                                 (3, 'Email notifications'),
#                                                 (3, 'Real-time report updates'),
#                                                 (3, 'Social media promotion'),
#                                                 (3, 'Featured placement'),
#                                                 (3, 'Priority support');


# ==============================================================================================================================


