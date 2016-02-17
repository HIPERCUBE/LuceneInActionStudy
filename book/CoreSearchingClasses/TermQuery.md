## TermQuery
**TermQuery** class has most basic function fo query.

```
Query q = new TermQuery(new Term("contents", "lucene"));
TopDocs hits = searcher.search(q, 10);
```

Next is [TopDocs](https://github.com/HIPERCUBE/LuceneInActionStudy/blob/master/book/CoreSearchingClasses/TopDocs.md) containing result of search.
