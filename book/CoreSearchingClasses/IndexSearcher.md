## IndexSearcher
**IndexSearcher** class is in charge of search as **IndexWriter**, and offers various search methods.

As **IndexSearcher** use index file, You should indicate **Directory** instance which have index at the constructor.

Some of searching methods are defined on **Searcher** abstract class.

Most simple ``search`` method require two argument: query, topN(Searcher return TopDocs as much of topN)

``` java
Directory dir = FSDirectory.open(new File("/tmp/index"));
IndexSearcher searcher = new IndexSearcher(dir);
Query q = new TermQuery(new Term("contents", "lucene"));
TopDocs hits = searcher.search(q, 10);
searcher.close();
```

Next is [Term](https://github.com/HIPERCUBE/LuceneInActionStudy/blob/master/book/CoreSearchingClasses/Term.md) basic unit of searching.
