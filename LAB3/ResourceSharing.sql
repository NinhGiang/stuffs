USE [master]
GO
/****** Object:  Database [ResourceSharing]    Script Date: 7/21/2020 10:40:28 PM ******/
CREATE DATABASE [ResourceSharing]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'ResourceSharing', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.SQLEXPRESS\MSSQL\DATA\ResourceSharing.mdf' , SIZE = 3072KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'ResourceSharing_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.SQLEXPRESS\MSSQL\DATA\ResourceSharing_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [ResourceSharing] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [ResourceSharing].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [ResourceSharing] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [ResourceSharing] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [ResourceSharing] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [ResourceSharing] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [ResourceSharing] SET ARITHABORT OFF 
GO
ALTER DATABASE [ResourceSharing] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [ResourceSharing] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [ResourceSharing] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [ResourceSharing] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [ResourceSharing] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [ResourceSharing] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [ResourceSharing] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [ResourceSharing] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [ResourceSharing] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [ResourceSharing] SET  DISABLE_BROKER 
GO
ALTER DATABASE [ResourceSharing] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [ResourceSharing] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [ResourceSharing] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [ResourceSharing] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [ResourceSharing] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [ResourceSharing] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [ResourceSharing] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [ResourceSharing] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [ResourceSharing] SET  MULTI_USER 
GO
ALTER DATABASE [ResourceSharing] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [ResourceSharing] SET DB_CHAINING OFF 
GO
ALTER DATABASE [ResourceSharing] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [ResourceSharing] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [ResourceSharing] SET DELAYED_DURABILITY = DISABLED 
GO
USE [ResourceSharing]
GO
/****** Object:  Table [dbo].[tblCategory]    Script Date: 7/21/2020 10:40:29 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblCategory](
	[categoryID] [int] NOT NULL,
	[name] [nvarchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[name] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblRequest]    Script Date: 7/21/2020 10:40:29 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblRequest](
	[requestID] [int] IDENTITY(1,1) NOT NULL,
	[userID] [nvarchar](50) NOT NULL,
	[fullname] [nvarchar](250) NOT NULL,
	[date] [date] NOT NULL,
	[status] [nvarchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[requestID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblRequestDetail]    Script Date: 7/21/2020 10:40:29 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblRequestDetail](
	[requestID] [int] NOT NULL,
	[resourceID] [int] NOT NULL,
	[resourceName] [nvarchar](250) NOT NULL,
	[quantity] [int] NOT NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblResource]    Script Date: 7/21/2020 10:40:29 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblResource](
	[resourceID] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](250) NOT NULL,
	[category] [nvarchar](50) NOT NULL,
	[fromDate] [date] NOT NULL,
	[toDate] [date] NOT NULL,
	[color] [nvarchar](50) NOT NULL,
	[quantity] [int] NOT NULL,
	[access] [bit] NOT NULL,
	[isActive] [bit] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[resourceID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblRole]    Script Date: 7/21/2020 10:40:29 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblRole](
	[roleID] [int] NOT NULL,
	[roleName] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_tblRole] PRIMARY KEY CLUSTERED 
(
	[roleID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblUser]    Script Date: 7/21/2020 10:40:29 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblUser](
	[userID] [nvarchar](50) NOT NULL,
	[password] [nvarchar](250) NOT NULL,
	[fullname] [nvarchar](250) NOT NULL,
	[phone] [bigint] NULL,
	[address] [nvarchar](250) NULL,
	[role] [int] NOT NULL,
	[status] [bit] NOT NULL,
	[date] [date] NOT NULL,
 CONSTRAINT [PK_tblUser] PRIMARY KEY CLUSTERED 
(
	[userID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[tblCategory] ([categoryID], [name]) VALUES (2, N'CSharp')
INSERT [dbo].[tblCategory] ([categoryID], [name]) VALUES (1, N'Java')
SET IDENTITY_INSERT [dbo].[tblRequest] ON 

INSERT [dbo].[tblRequest] ([requestID], [userID], [fullname], [date], [status]) VALUES (1, N'giang', N'ha thi ninh giang', CAST(N'2020-07-16' AS Date), N'Accept')
INSERT [dbo].[tblRequest] ([requestID], [userID], [fullname], [date], [status]) VALUES (2, N'giang', N'ha thi ninh giang', CAST(N'2020-07-16' AS Date), N'Decline')
INSERT [dbo].[tblRequest] ([requestID], [userID], [fullname], [date], [status]) VALUES (3, N'giang', N'ha thi ninh giang', CAST(N'2020-07-16' AS Date), N'Inactive')
INSERT [dbo].[tblRequest] ([requestID], [userID], [fullname], [date], [status]) VALUES (4, N'giang', N'ha thi ninh giang', CAST(N'2020-07-16' AS Date), N'Decline')
INSERT [dbo].[tblRequest] ([requestID], [userID], [fullname], [date], [status]) VALUES (5, N'uyen', N'nguyen le phuong uyen', CAST(N'2020-07-20' AS Date), N'Inactive')
INSERT [dbo].[tblRequest] ([requestID], [userID], [fullname], [date], [status]) VALUES (6, N'uyen', N'nguyen le phuong uyen', CAST(N'2020-07-21' AS Date), N'Inactive')
INSERT [dbo].[tblRequest] ([requestID], [userID], [fullname], [date], [status]) VALUES (7, N'giang', N'ha thi ninh giang', CAST(N'2020-07-21' AS Date), N'New')
INSERT [dbo].[tblRequest] ([requestID], [userID], [fullname], [date], [status]) VALUES (8, N'giang', N'ha thi ninh giang', CAST(N'2020-07-21' AS Date), N'New')
INSERT [dbo].[tblRequest] ([requestID], [userID], [fullname], [date], [status]) VALUES (9, N'giang', N'ha thi ninh giang', CAST(N'2020-07-21' AS Date), N'New')
INSERT [dbo].[tblRequest] ([requestID], [userID], [fullname], [date], [status]) VALUES (10, N'giang', N'ha thi ninh giang', CAST(N'2020-07-21' AS Date), N'New')
INSERT [dbo].[tblRequest] ([requestID], [userID], [fullname], [date], [status]) VALUES (11, N'uyen', N'nguyen le phuong uyen', CAST(N'2020-07-21' AS Date), N'Inactive')
INSERT [dbo].[tblRequest] ([requestID], [userID], [fullname], [date], [status]) VALUES (12, N'uyen', N'nguyen le phuong uyen', CAST(N'2020-07-21' AS Date), N'Inactive')
INSERT [dbo].[tblRequest] ([requestID], [userID], [fullname], [date], [status]) VALUES (13, N'uyen', N'nguyen le phuong uyen', CAST(N'2020-07-21' AS Date), N'Inactive')
SET IDENTITY_INSERT [dbo].[tblRequest] OFF
INSERT [dbo].[tblRequestDetail] ([requestID], [resourceID], [resourceName], [quantity]) VALUES (4, 2, N'Tai Lieu Tu Hoc C# .NET', 3)
INSERT [dbo].[tblRequestDetail] ([requestID], [resourceID], [resourceName], [quantity]) VALUES (4, 1, N'Tai Lieu Hoc Java Web Application', 2)
INSERT [dbo].[tblRequestDetail] ([requestID], [resourceID], [resourceName], [quantity]) VALUES (1, 1, N'Tai Lieu Hoc Java Web Application', 4)
INSERT [dbo].[tblRequestDetail] ([requestID], [resourceID], [resourceName], [quantity]) VALUES (2, 1, N'Tai Lieu Hoc Java Web Application', 1)
INSERT [dbo].[tblRequestDetail] ([requestID], [resourceID], [resourceName], [quantity]) VALUES (2, 2, N'Tai Lieu Tu Hoc C#.NET', 5)
INSERT [dbo].[tblRequestDetail] ([requestID], [resourceID], [resourceName], [quantity]) VALUES (3, 2, N'Tai Lieu Tu Hoc C#.NET', 1)
INSERT [dbo].[tblRequestDetail] ([requestID], [resourceID], [resourceName], [quantity]) VALUES (5, 2, N'Tai Lieu Tu Hoc C# .NET', 10)
INSERT [dbo].[tblRequestDetail] ([requestID], [resourceID], [resourceName], [quantity]) VALUES (6, 1, N'Tai Lieu Hoc Java Web Application', 3)
INSERT [dbo].[tblRequestDetail] ([requestID], [resourceID], [resourceName], [quantity]) VALUES (6, 2, N'Tai Lieu Tu Hoc C# .NET', 3)
INSERT [dbo].[tblRequestDetail] ([requestID], [resourceID], [resourceName], [quantity]) VALUES (12, 1, N'Tai Lieu Hoc Java Web Application', 1)
INSERT [dbo].[tblRequestDetail] ([requestID], [resourceID], [resourceName], [quantity]) VALUES (12, 2, N'Tai Lieu Tu Hoc C# .NET', 1)
INSERT [dbo].[tblRequestDetail] ([requestID], [resourceID], [resourceName], [quantity]) VALUES (13, 1, N'Tai Lieu Hoc Java Web Application', 1)
INSERT [dbo].[tblRequestDetail] ([requestID], [resourceID], [resourceName], [quantity]) VALUES (13, 2, N'Tai Lieu Tu Hoc C# .NET', 1)
INSERT [dbo].[tblRequestDetail] ([requestID], [resourceID], [resourceName], [quantity]) VALUES (10, 1, N'Tai Lieu Hoc Java Web Application', 10)
INSERT [dbo].[tblRequestDetail] ([requestID], [resourceID], [resourceName], [quantity]) VALUES (10, 2, N'Tai Lieu Tu Hoc C# .NET', 8)
INSERT [dbo].[tblRequestDetail] ([requestID], [resourceID], [resourceName], [quantity]) VALUES (11, 1, N'Tai Lieu Hoc Java Web Application', 1)
INSERT [dbo].[tblRequestDetail] ([requestID], [resourceID], [resourceName], [quantity]) VALUES (11, 2, N'Tai Lieu Tu Hoc C# .NET', 1)
SET IDENTITY_INSERT [dbo].[tblResource] ON 

INSERT [dbo].[tblResource] ([resourceID], [name], [category], [fromDate], [toDate], [color], [quantity], [access], [isActive]) VALUES (1, N'Tai Lieu Hoc Java Web Application', N'Java', CAST(N'2020-12-31' AS Date), CAST(N'2020-06-01' AS Date), N'Red', 15, 1, 1)
INSERT [dbo].[tblResource] ([resourceID], [name], [category], [fromDate], [toDate], [color], [quantity], [access], [isActive]) VALUES (2, N'Tai Lieu Tu Hoc C# .NET', N'CSharp', CAST(N'2020-12-31' AS Date), CAST(N'2020-08-01' AS Date), N'Blue', 3, 0, 1)
SET IDENTITY_INSERT [dbo].[tblResource] OFF
INSERT [dbo].[tblRole] ([roleID], [roleName]) VALUES (0, N'New')
INSERT [dbo].[tblRole] ([roleID], [roleName]) VALUES (1, N'Employee')
INSERT [dbo].[tblRole] ([roleID], [roleName]) VALUES (2, N'Administrator')
INSERT [dbo].[tblRole] ([roleID], [roleName]) VALUES (3, N'Manager')
INSERT [dbo].[tblUser] ([userID], [password], [fullname], [phone], [address], [role], [status], [date]) VALUES (N'dung', N'dung', N'ha anh dung', 938904364, N'abcxyz', 3, 1, CAST(N'2020-10-10' AS Date))
INSERT [dbo].[tblUser] ([userID], [password], [fullname], [phone], [address], [role], [status], [date]) VALUES (N'giang', N'giang', N'ha thi ninh giang', 938868011, N'abcxyz', 1, 1, CAST(N'2020-11-11' AS Date))
INSERT [dbo].[tblUser] ([userID], [password], [fullname], [phone], [address], [role], [status], [date]) VALUES (N'giang@giang.giang', N'gianggiang', N'ha thi ninh giang', NULL, NULL, 0, 1, CAST(N'2020-07-21' AS Date))
INSERT [dbo].[tblUser] ([userID], [password], [fullname], [phone], [address], [role], [status], [date]) VALUES (N'gianghtnse140115@fpt.edu.vn', N'qwerty', N'ha thi ninh giang', 938868011, NULL, 1, 1, CAST(N'2020-07-21' AS Date))
INSERT [dbo].[tblUser] ([userID], [password], [fullname], [phone], [address], [role], [status], [date]) VALUES (N'uyen', N'uyen', N'nguyen le phuong uyen', 123456789, N'abcxyz', 2, 1, CAST(N'2020-07-20' AS Date))
ALTER TABLE [dbo].[tblRequest]  WITH CHECK ADD  CONSTRAINT [FK_tblRequest_tblUser] FOREIGN KEY([userID])
REFERENCES [dbo].[tblUser] ([userID])
GO
ALTER TABLE [dbo].[tblRequest] CHECK CONSTRAINT [FK_tblRequest_tblUser]
GO
ALTER TABLE [dbo].[tblRequestDetail]  WITH CHECK ADD  CONSTRAINT [FK_tblRequestDetail_tblRequest] FOREIGN KEY([requestID])
REFERENCES [dbo].[tblRequest] ([requestID])
GO
ALTER TABLE [dbo].[tblRequestDetail] CHECK CONSTRAINT [FK_tblRequestDetail_tblRequest]
GO
ALTER TABLE [dbo].[tblRequestDetail]  WITH CHECK ADD  CONSTRAINT [FK_tblRequestDetail_tblResource] FOREIGN KEY([resourceID])
REFERENCES [dbo].[tblResource] ([resourceID])
GO
ALTER TABLE [dbo].[tblRequestDetail] CHECK CONSTRAINT [FK_tblRequestDetail_tblResource]
GO
ALTER TABLE [dbo].[tblResource]  WITH CHECK ADD  CONSTRAINT [FK_tblResource_tblCategory] FOREIGN KEY([category])
REFERENCES [dbo].[tblCategory] ([name])
GO
ALTER TABLE [dbo].[tblResource] CHECK CONSTRAINT [FK_tblResource_tblCategory]
GO
ALTER TABLE [dbo].[tblUser]  WITH CHECK ADD  CONSTRAINT [FK_tblUser_tblRole] FOREIGN KEY([role])
REFERENCES [dbo].[tblRole] ([roleID])
GO
ALTER TABLE [dbo].[tblUser] CHECK CONSTRAINT [FK_tblUser_tblRole]
GO
USE [master]
GO
ALTER DATABASE [ResourceSharing] SET  READ_WRITE 
GO
