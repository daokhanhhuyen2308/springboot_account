USE [master]
GO
/****** Object:  Database [account]    Script Date: 5/23/2024 5:03:42 PM ******/
CREATE DATABASE [account]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'account', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.SQLEXPRESS\MSSQL\DATA\account.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'account_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.SQLEXPRESS\MSSQL\DATA\account_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT, LEDGER = OFF
GO
ALTER DATABASE [account] SET COMPATIBILITY_LEVEL = 160
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [account].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [account] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [account] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [account] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [account] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [account] SET ARITHABORT OFF 
GO
ALTER DATABASE [account] SET AUTO_CLOSE ON 
GO
ALTER DATABASE [account] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [account] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [account] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [account] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [account] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [account] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [account] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [account] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [account] SET  ENABLE_BROKER 
GO
ALTER DATABASE [account] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [account] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [account] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [account] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [account] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [account] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [account] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [account] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [account] SET  MULTI_USER 
GO
ALTER DATABASE [account] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [account] SET DB_CHAINING OFF 
GO
ALTER DATABASE [account] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [account] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [account] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [account] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [account] SET QUERY_STORE = ON
GO
ALTER DATABASE [account] SET QUERY_STORE (OPERATION_MODE = READ_WRITE, CLEANUP_POLICY = (STALE_QUERY_THRESHOLD_DAYS = 30), DATA_FLUSH_INTERVAL_SECONDS = 900, INTERVAL_LENGTH_MINUTES = 60, MAX_STORAGE_SIZE_MB = 1000, QUERY_CAPTURE_MODE = AUTO, SIZE_BASED_CLEANUP_MODE = AUTO, MAX_PLANS_PER_QUERY = 200, WAIT_STATS_CAPTURE_MODE = ON)
GO
USE [account]
GO
/****** Object:  Table [dbo].[account]    Script Date: 5/23/2024 5:03:42 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[account](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[email] [varchar](255) NULL,
	[password] [varchar](255) NULL,
	[username] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[account_role]    Script Date: 5/23/2024 5:03:42 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[account_role](
	[account_id] [int] NOT NULL,
	[role_id] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[account_id] ASC,
	[role_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[color]    Script Date: 5/23/2024 5:03:42 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[color](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[product]    Script Date: 5/23/2024 5:03:42 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[product](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[price] [float] NOT NULL,
	[image] [varchar](255) NULL,
	[name] [varchar](255) NULL,
	[type] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[product_color]    Script Date: 5/23/2024 5:03:42 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[product_color](
	[color_id] [int] NOT NULL,
	[product_id] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[color_id] ASC,
	[product_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[product_size]    Script Date: 5/23/2024 5:03:42 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[product_size](
	[product_id] [int] NOT NULL,
	[size_id] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[product_id] ASC,
	[size_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[role]    Script Date: 5/23/2024 5:03:42 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[role](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[size]    Script Date: 5/23/2024 5:03:42 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[size](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[account] ON 

INSERT [dbo].[account] ([id], [email], [password], [username]) VALUES (1, N'mai2003@gmail.com', N'$2a$10$MHvFCKlLSluJJCP0LtCH8eDwfQfrSs6Smvd63eEjV3NhxuxADa0Ii', N'maimai')
INSERT [dbo].[account] ([id], [email], [password], [username]) VALUES (2, N'huyen2003@gmail.com', N'$2a$10$CcOptztJHLiYPVp9MEaxmeF3tAcGsDTUliB8rTUBwqraVvVV4Z9Vq', N'huyen2003')
INSERT [dbo].[account] ([id], [email], [password], [username]) VALUES (3, N'anh30072003@gmail.com', N'$2a$10$fKbIeh2amyt9Cnf3jiX7/utasU6DLFhec.xAoLYxh24MUfaXf9AtG', N'anh3007')
INSERT [dbo].[account] ([id], [email], [password], [username]) VALUES (4, N'anbi2003@gmail.com', N'$2a$10$adNNYJNoQv98bNLOYGreFeCCL9jIiskCprBgLLnGyeqjv.IkuQMbu', N'anbi2003')
SET IDENTITY_INSERT [dbo].[account] OFF
GO
INSERT [dbo].[account_role] ([account_id], [role_id]) VALUES (1, 1)
INSERT [dbo].[account_role] ([account_id], [role_id]) VALUES (2, 1)
INSERT [dbo].[account_role] ([account_id], [role_id]) VALUES (3, 3)
INSERT [dbo].[account_role] ([account_id], [role_id]) VALUES (4, 3)
GO
SET IDENTITY_INSERT [dbo].[color] ON 

INSERT [dbo].[color] ([id], [name]) VALUES (1, N'Blue')
INSERT [dbo].[color] ([id], [name]) VALUES (2, N'Black')
INSERT [dbo].[color] ([id], [name]) VALUES (3, N'White')
INSERT [dbo].[color] ([id], [name]) VALUES (4, N'Red')
INSERT [dbo].[color] ([id], [name]) VALUES (5, N'Yellow')
INSERT [dbo].[color] ([id], [name]) VALUES (6, N'Brown')
INSERT [dbo].[color] ([id], [name]) VALUES (7, N'Gray')
INSERT [dbo].[color] ([id], [name]) VALUES (8, N'Pink')
INSERT [dbo].[color] ([id], [name]) VALUES (9, N'Salmon')
INSERT [dbo].[color] ([id], [name]) VALUES (10, N'Purple')
INSERT [dbo].[color] ([id], [name]) VALUES (11, N'Green')
INSERT [dbo].[color] ([id], [name]) VALUES (12, N'Orange')
SET IDENTITY_INSERT [dbo].[color] OFF
GO
SET IDENTITY_INSERT [dbo].[product] ON 

INSERT [dbo].[product] ([id], [price], [image], [name], [type]) VALUES (1, 290000, N'imgs/1.png', N'Product 1', N'pant')
INSERT [dbo].[product] ([id], [price], [image], [name], [type]) VALUES (2, 390000, N'imgs/2.png', N'Product 2', N'shirt')
INSERT [dbo].[product] ([id], [price], [image], [name], [type]) VALUES (3, 490000, N'imgs/3.png', N'Product 3', N'jacket')
INSERT [dbo].[product] ([id], [price], [image], [name], [type]) VALUES (4, 450000, N'imgs/4.png', N'Product 4', N'jacket')
INSERT [dbo].[product] ([id], [price], [image], [name], [type]) VALUES (5, 550000, N'imgs/5.png', N'Product 5', N't-shirt')
INSERT [dbo].[product] ([id], [price], [image], [name], [type]) VALUES (6, 390000, N'imgs/6.png', N'Product 6', N'shirt')
INSERT [dbo].[product] ([id], [price], [image], [name], [type]) VALUES (7, 300000, N'imgs/7.png', N'Product 7', N'jacket')
INSERT [dbo].[product] ([id], [price], [image], [name], [type]) VALUES (8, 600000, N'imgs/8.png', N'Product 8', N'shirt')
INSERT [dbo].[product] ([id], [price], [image], [name], [type]) VALUES (9, 650000, N'imgs/9.png', N'Product 9', N'pant')
INSERT [dbo].[product] ([id], [price], [image], [name], [type]) VALUES (10, 350000, N'imgs/10.png', N'Product 10', N't-shirt')
INSERT [dbo].[product] ([id], [price], [image], [name], [type]) VALUES (11, 450000, N'imgs/11.png', N'Product 11', N'shirt')
INSERT [dbo].[product] ([id], [price], [image], [name], [type]) VALUES (12, 500000, N'imgs/12.png', N'Product 12', N'pant')
INSERT [dbo].[product] ([id], [price], [image], [name], [type]) VALUES (13, 500000, N'imgs/12.png', N'Product 13', N'pant')
INSERT [dbo].[product] ([id], [price], [image], [name], [type]) VALUES (14, 500000, N'imgs/14.png', N'Product 14', N'pant')
INSERT [dbo].[product] ([id], [price], [image], [name], [type]) VALUES (15, 500000, N'imgs/15.png', N'Product 15', N'jacket')
INSERT [dbo].[product] ([id], [price], [image], [name], [type]) VALUES (16, 490000, N'imgs/16.png', N'Product 16', N't-shirt')
SET IDENTITY_INSERT [dbo].[product] OFF
GO
INSERT [dbo].[product_color] ([color_id], [product_id]) VALUES (1, 2)
INSERT [dbo].[product_color] ([color_id], [product_id]) VALUES (1, 3)
INSERT [dbo].[product_color] ([color_id], [product_id]) VALUES (1, 4)
INSERT [dbo].[product_color] ([color_id], [product_id]) VALUES (1, 8)
INSERT [dbo].[product_color] ([color_id], [product_id]) VALUES (1, 9)
INSERT [dbo].[product_color] ([color_id], [product_id]) VALUES (1, 10)
INSERT [dbo].[product_color] ([color_id], [product_id]) VALUES (1, 11)
INSERT [dbo].[product_color] ([color_id], [product_id]) VALUES (1, 12)
INSERT [dbo].[product_color] ([color_id], [product_id]) VALUES (1, 13)
INSERT [dbo].[product_color] ([color_id], [product_id]) VALUES (1, 14)
INSERT [dbo].[product_color] ([color_id], [product_id]) VALUES (1, 15)
INSERT [dbo].[product_color] ([color_id], [product_id]) VALUES (2, 1)
INSERT [dbo].[product_color] ([color_id], [product_id]) VALUES (2, 2)
INSERT [dbo].[product_color] ([color_id], [product_id]) VALUES (2, 3)
INSERT [dbo].[product_color] ([color_id], [product_id]) VALUES (2, 4)
INSERT [dbo].[product_color] ([color_id], [product_id]) VALUES (2, 5)
INSERT [dbo].[product_color] ([color_id], [product_id]) VALUES (2, 16)
INSERT [dbo].[product_color] ([color_id], [product_id]) VALUES (3, 1)
INSERT [dbo].[product_color] ([color_id], [product_id]) VALUES (3, 2)
INSERT [dbo].[product_color] ([color_id], [product_id]) VALUES (3, 7)
INSERT [dbo].[product_color] ([color_id], [product_id]) VALUES (3, 8)
INSERT [dbo].[product_color] ([color_id], [product_id]) VALUES (4, 1)
INSERT [dbo].[product_color] ([color_id], [product_id]) VALUES (5, 3)
INSERT [dbo].[product_color] ([color_id], [product_id]) VALUES (6, 5)
INSERT [dbo].[product_color] ([color_id], [product_id]) VALUES (6, 6)
INSERT [dbo].[product_color] ([color_id], [product_id]) VALUES (6, 11)
INSERT [dbo].[product_color] ([color_id], [product_id]) VALUES (6, 12)
INSERT [dbo].[product_color] ([color_id], [product_id]) VALUES (6, 13)
INSERT [dbo].[product_color] ([color_id], [product_id]) VALUES (6, 14)
INSERT [dbo].[product_color] ([color_id], [product_id]) VALUES (6, 15)
INSERT [dbo].[product_color] ([color_id], [product_id]) VALUES (6, 16)
INSERT [dbo].[product_color] ([color_id], [product_id]) VALUES (7, 4)
INSERT [dbo].[product_color] ([color_id], [product_id]) VALUES (8, 7)
INSERT [dbo].[product_color] ([color_id], [product_id]) VALUES (8, 16)
INSERT [dbo].[product_color] ([color_id], [product_id]) VALUES (10, 6)
INSERT [dbo].[product_color] ([color_id], [product_id]) VALUES (10, 10)
INSERT [dbo].[product_color] ([color_id], [product_id]) VALUES (10, 11)
INSERT [dbo].[product_color] ([color_id], [product_id]) VALUES (11, 7)
INSERT [dbo].[product_color] ([color_id], [product_id]) VALUES (11, 12)
INSERT [dbo].[product_color] ([color_id], [product_id]) VALUES (11, 13)
INSERT [dbo].[product_color] ([color_id], [product_id]) VALUES (11, 14)
INSERT [dbo].[product_color] ([color_id], [product_id]) VALUES (11, 16)
INSERT [dbo].[product_color] ([color_id], [product_id]) VALUES (12, 5)
INSERT [dbo].[product_color] ([color_id], [product_id]) VALUES (12, 6)
INSERT [dbo].[product_color] ([color_id], [product_id]) VALUES (12, 7)
INSERT [dbo].[product_color] ([color_id], [product_id]) VALUES (12, 8)
INSERT [dbo].[product_color] ([color_id], [product_id]) VALUES (12, 9)
INSERT [dbo].[product_color] ([color_id], [product_id]) VALUES (12, 10)
GO
INSERT [dbo].[product_size] ([product_id], [size_id]) VALUES (1, 1)
INSERT [dbo].[product_size] ([product_id], [size_id]) VALUES (1, 2)
INSERT [dbo].[product_size] ([product_id], [size_id]) VALUES (1, 4)
INSERT [dbo].[product_size] ([product_id], [size_id]) VALUES (2, 1)
INSERT [dbo].[product_size] ([product_id], [size_id]) VALUES (2, 2)
INSERT [dbo].[product_size] ([product_id], [size_id]) VALUES (2, 4)
INSERT [dbo].[product_size] ([product_id], [size_id]) VALUES (3, 1)
INSERT [dbo].[product_size] ([product_id], [size_id]) VALUES (3, 2)
INSERT [dbo].[product_size] ([product_id], [size_id]) VALUES (3, 4)
INSERT [dbo].[product_size] ([product_id], [size_id]) VALUES (4, 1)
INSERT [dbo].[product_size] ([product_id], [size_id]) VALUES (4, 2)
INSERT [dbo].[product_size] ([product_id], [size_id]) VALUES (4, 4)
INSERT [dbo].[product_size] ([product_id], [size_id]) VALUES (5, 1)
INSERT [dbo].[product_size] ([product_id], [size_id]) VALUES (5, 2)
INSERT [dbo].[product_size] ([product_id], [size_id]) VALUES (5, 4)
INSERT [dbo].[product_size] ([product_id], [size_id]) VALUES (6, 1)
INSERT [dbo].[product_size] ([product_id], [size_id]) VALUES (6, 2)
INSERT [dbo].[product_size] ([product_id], [size_id]) VALUES (6, 4)
INSERT [dbo].[product_size] ([product_id], [size_id]) VALUES (7, 1)
INSERT [dbo].[product_size] ([product_id], [size_id]) VALUES (7, 2)
INSERT [dbo].[product_size] ([product_id], [size_id]) VALUES (7, 4)
INSERT [dbo].[product_size] ([product_id], [size_id]) VALUES (8, 2)
INSERT [dbo].[product_size] ([product_id], [size_id]) VALUES (8, 4)
INSERT [dbo].[product_size] ([product_id], [size_id]) VALUES (9, 2)
INSERT [dbo].[product_size] ([product_id], [size_id]) VALUES (9, 4)
INSERT [dbo].[product_size] ([product_id], [size_id]) VALUES (10, 1)
INSERT [dbo].[product_size] ([product_id], [size_id]) VALUES (10, 2)
INSERT [dbo].[product_size] ([product_id], [size_id]) VALUES (10, 4)
INSERT [dbo].[product_size] ([product_id], [size_id]) VALUES (11, 1)
INSERT [dbo].[product_size] ([product_id], [size_id]) VALUES (11, 2)
INSERT [dbo].[product_size] ([product_id], [size_id]) VALUES (11, 4)
INSERT [dbo].[product_size] ([product_id], [size_id]) VALUES (12, 1)
INSERT [dbo].[product_size] ([product_id], [size_id]) VALUES (12, 2)
INSERT [dbo].[product_size] ([product_id], [size_id]) VALUES (12, 4)
INSERT [dbo].[product_size] ([product_id], [size_id]) VALUES (13, 1)
INSERT [dbo].[product_size] ([product_id], [size_id]) VALUES (13, 2)
INSERT [dbo].[product_size] ([product_id], [size_id]) VALUES (13, 4)
INSERT [dbo].[product_size] ([product_id], [size_id]) VALUES (14, 1)
INSERT [dbo].[product_size] ([product_id], [size_id]) VALUES (14, 2)
INSERT [dbo].[product_size] ([product_id], [size_id]) VALUES (14, 4)
INSERT [dbo].[product_size] ([product_id], [size_id]) VALUES (15, 1)
INSERT [dbo].[product_size] ([product_id], [size_id]) VALUES (15, 2)
INSERT [dbo].[product_size] ([product_id], [size_id]) VALUES (15, 4)
INSERT [dbo].[product_size] ([product_id], [size_id]) VALUES (16, 1)
INSERT [dbo].[product_size] ([product_id], [size_id]) VALUES (16, 2)
INSERT [dbo].[product_size] ([product_id], [size_id]) VALUES (16, 4)
GO
SET IDENTITY_INSERT [dbo].[role] ON 

INSERT [dbo].[role] ([id], [name]) VALUES (1, N'ADMIN')
INSERT [dbo].[role] ([id], [name]) VALUES (2, N'MANAGER')
INSERT [dbo].[role] ([id], [name]) VALUES (3, N'USER')
SET IDENTITY_INSERT [dbo].[role] OFF
GO
SET IDENTITY_INSERT [dbo].[size] ON 

INSERT [dbo].[size] ([id], [name]) VALUES (1, N'S')
INSERT [dbo].[size] ([id], [name]) VALUES (2, N'M')
INSERT [dbo].[size] ([id], [name]) VALUES (3, N'L')
INSERT [dbo].[size] ([id], [name]) VALUES (4, N'XL')
INSERT [dbo].[size] ([id], [name]) VALUES (5, N'XXL')
SET IDENTITY_INSERT [dbo].[size] OFF
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UK_gex1lmaqpg0ir5g1f5eftyaa1]    Script Date: 5/23/2024 5:03:42 PM ******/
CREATE UNIQUE NONCLUSTERED INDEX [UK_gex1lmaqpg0ir5g1f5eftyaa1] ON [dbo].[account]
(
	[username] ASC
)
WHERE ([username] IS NOT NULL)
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UK_q0uja26qgu1atulenwup9rxyr]    Script Date: 5/23/2024 5:03:42 PM ******/
CREATE UNIQUE NONCLUSTERED INDEX [UK_q0uja26qgu1atulenwup9rxyr] ON [dbo].[account]
(
	[email] ASC
)
WHERE ([email] IS NOT NULL)
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
ALTER TABLE [dbo].[account_role]  WITH CHECK ADD  CONSTRAINT [FK1f8y4iy71kb1arff79s71j0dh] FOREIGN KEY([account_id])
REFERENCES [dbo].[account] ([id])
GO
ALTER TABLE [dbo].[account_role] CHECK CONSTRAINT [FK1f8y4iy71kb1arff79s71j0dh]
GO
ALTER TABLE [dbo].[account_role]  WITH CHECK ADD  CONSTRAINT [FKrs2s3m3039h0xt8d5yhwbuyam] FOREIGN KEY([role_id])
REFERENCES [dbo].[role] ([id])
GO
ALTER TABLE [dbo].[account_role] CHECK CONSTRAINT [FKrs2s3m3039h0xt8d5yhwbuyam]
GO
ALTER TABLE [dbo].[product_color]  WITH CHECK ADD  CONSTRAINT [FK3iys6jgmsdkw7w5ncgm55wgj3] FOREIGN KEY([color_id])
REFERENCES [dbo].[color] ([id])
GO
ALTER TABLE [dbo].[product_color] CHECK CONSTRAINT [FK3iys6jgmsdkw7w5ncgm55wgj3]
GO
ALTER TABLE [dbo].[product_color]  WITH CHECK ADD  CONSTRAINT [FKqb6lncpndi0w5po3rr5r9up5e] FOREIGN KEY([product_id])
REFERENCES [dbo].[product] ([id])
GO
ALTER TABLE [dbo].[product_color] CHECK CONSTRAINT [FKqb6lncpndi0w5po3rr5r9up5e]
GO
ALTER TABLE [dbo].[product_size]  WITH CHECK ADD  CONSTRAINT [FK8i3jm2ctt0lsyeik2wt76yvv0] FOREIGN KEY([product_id])
REFERENCES [dbo].[product] ([id])
GO
ALTER TABLE [dbo].[product_size] CHECK CONSTRAINT [FK8i3jm2ctt0lsyeik2wt76yvv0]
GO
ALTER TABLE [dbo].[product_size]  WITH CHECK ADD  CONSTRAINT [FKnlkh5xcjuopsnoimdvmumioia] FOREIGN KEY([size_id])
REFERENCES [dbo].[size] ([id])
GO
ALTER TABLE [dbo].[product_size] CHECK CONSTRAINT [FKnlkh5xcjuopsnoimdvmumioia]
GO
USE [master]
GO
ALTER DATABASE [account] SET  READ_WRITE 
GO
