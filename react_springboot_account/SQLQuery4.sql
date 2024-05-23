SELECT TOP (1000) [id]
      ,[price]
      ,[image]
      ,[name]
      ,[type]
  FROM [account].[dbo].[product]


  select count(p.id) as N'Tổng' from product p