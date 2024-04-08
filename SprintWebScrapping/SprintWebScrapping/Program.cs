using CsvHelper;
using CsvHelper.Configuration;
using System;
using System.Collections.Generic;
using System.Globalization;
using System.IO;
using System.Net.Http;
using HtmlAgilityPack;

namespace WebScraper
{
    class Program
    {
        static void Main(string[] args)
        {
            try
            {
                string url = "https://www.dell.com/pt-br/shop/notebooks-dell/sr/laptops/notebook?appliedRefinements=37791";
                var httpClient = new HttpClient();
                var html = httpClient.GetStringAsync(url).Result;
                var htmlDocument = new HtmlDocument();
                htmlDocument.LoadHtml(html);

                var notebooks = new List<Notebook>();

                // Notebook 1
                var titleElement1 = htmlDocument.DocumentNode.SelectSingleNode("//*[@id=\"i3520w1014w\"]/section[2]/div[1]/h3/a");
                var title1 = titleElement1?.InnerText?.Trim();
                var priceElement1 = htmlDocument.DocumentNode.SelectSingleNode("//*[@id=\"i3520w1014w\"]/section[3]/div[1]/div[3]/span[2]");
                var price1 = priceElement1?.InnerText?.Trim();

                // Notebook 2
                var titleElement2 = htmlDocument.DocumentNode.SelectSingleNode("//*[@id=\"g5530w003w\"]/section[2]/div[1]/h3/a");
                var title2 = titleElement2?.InnerText?.Trim();
                var priceElement2 = htmlDocument.DocumentNode.SelectSingleNode("//*[@id=\"g5530w003w\"]/section[3]/div[1]/div[3]/span[2]");
                var price2 = priceElement2?.InnerText?.Trim();

                // Notebook 3
                var titleElement3 = htmlDocument.DocumentNode.SelectSingleNode("//*[@id=\"i3530w1108bts\"]/section[2]/div[1]/h3/a");
                var title3 = titleElement3?.InnerText?.Trim();
                var priceElement3 = htmlDocument.DocumentNode.SelectSingleNode("//*[@id=\"i3530w1108bts\"]/section[2]/div[2]/div[3]/span[2]");
                var price3 = priceElement3?.InnerText?.Trim();

                // Adicionando os dados à lista
                notebooks.Add(new Notebook { Title = title1, Price = price1 });
                notebooks.Add(new Notebook { Title = title2, Price = price2 });
                notebooks.Add(new Notebook { Title = title3, Price = price3 });

                // Escrevendo os dados em um arquivo CSV
                using (var writer = new StreamWriter(@"C:\CSV_WS\notebooks.csv"))
                using (var csv = new CsvWriter(writer, CultureInfo.InvariantCulture))
                {
                    csv.WriteRecords(notebooks);
                }

                Console.WriteLine("Dados exportados para notebooks.csv com sucesso!");
            }
            catch (HttpRequestException e)
            {
                Console.WriteLine("Erro ao buscar URL: " + e.Message);
            }
            catch (Exception ex)
            {
                Console.WriteLine("Ocorreu um erro: " + ex.Message);
            }
        }

        public class Notebook
        {
            public string Title { get; set; }
            public string Price { get; set; }
        }
    }
}
