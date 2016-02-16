## Directory

 - **Directory** represents the location of Lucene index.
 - It's an abstract class that allows it subclasses to store the index as they see fit.
 - At the [Indexer](https://github.com/HIPERCUBE/LuceneInActionStudy/tree/master/project/Indexer) project, we used a path to an actual file system directory to obtain an instance of **Directory**, which passed to [IndexWriter](https://github.com/HIPERCUBE/LuceneInActionStudy/tree/master/book/CoreIndexingClasses/IndexWriter.md)'s constructor.
 - Lucence has a various **Directory**.
 - [IndexWriter](https://github.com/HIPERCUBE/LuceneInActionStudy/tree/master/book/CoreIndexingClasses/IndexWriter.md) can work when words be seprated from the data by [Analyzer](https://github.com/HIPERCUBE/LuceneInActionStudy/tree/master/book/CoreIndexingClasses/Analyzer.md).
