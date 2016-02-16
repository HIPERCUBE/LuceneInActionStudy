## Analyzer

 - Before text is indexed, it's passed through an **Analyzer**.
 - The **Analyzer**, specified in the **IndexWriter** constructor, is in chargre of extracting tokens out of text to be indexed and eliminating the rest.
 - If the content to be indexed isn't plain text, it should first be converted to it.
 - **Analyzer** is also an abstract class, but Lucene comes with several implementations of it.
 - Some of them deal with skipping stop words(such as a, an, the, in, and on)
 - In the analyze phase, it require a [Document](https://github.com/HIPERCUBE/LuceneInActionStudy/tree/master/book/CoreIndexingClasses/Document.md) which has fields to index.
