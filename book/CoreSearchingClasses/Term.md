## Term
**Term** is most basic unit of searching.

It comprises a pair offield name and particular word in the field as **Field**.

You can use **Term&& to query, by [TermQuery](https://github.com/HIPERCUBE/LuceneInActionStudy/blob/master/book/CoreSearchingClasses/TermQuery.md).

``` java
Query q = new TermQuery(new Term("contents", "lucene"));
TopDocs hits = searcher.search(q, 10);
```

This sample search "lucene" at the "contents" field, and bring top 10 document related to the criteria in descending order.
