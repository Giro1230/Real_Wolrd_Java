CREATE TABLE tag
(
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  tagName VARCHAR(13) NOT NULL UNIQUE,
  created_at DATETIME NOT NULL,
  updated_at DATETIME
);
