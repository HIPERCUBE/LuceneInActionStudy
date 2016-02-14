package com.hipercube.indexer

import org.apache.lucene.analysis.standard.StandardAnalyzer
import org.apache.lucene.document.Document
import org.apache.lucene.document.Field
import org.apache.lucene.index.IndexWriter
import org.apache.lucene.store.Directory
import org.apache.lucene.store.FSDirectory
import org.apache.lucene.util.Version

/**
 * Copyright (c) 2/7/16 Joowon Ryoo
 * <p>
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following
 * conditions:
 * <p>
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */

class Indexer {
    static void main(String[] args) throws Exception {
        if (args.length != 2)
            throw new IllegalArgumentException("Usage: java ${Indexer.getName()} <index dir> <data dir>")

        String indexDir = args[0]
        String dataDir = args[1]

        long start = System.currentTimeMillis()
        Indexer indexer = new Indexer(indexDir)
        int numIndexed

        try {
            numIndexed = indexer.index(dataDir, new TextFilesFilter())
        } finally {
            indexer.close()
        }

        long end = System.currentTimeMillis()

        System.out.println("Indexing ${numIndexed} files took ${end - start} milliseconds")
    }


    private IndexWriter writer

    Indexer(String indexDir) throws IOException {
        Directory dir = FSDirectory.open(new File(indexDir))
        writer = new IndexWriter(dir, new StandardAnalyzer(Version.LUCENE_30), true, IndexWriter.MaxFieldLength.UNLIMITED)
    }

    void close() throws IOException { writer.close() }

    int index(String dataDir, FileFilter filter) throws Exception {
        File[] files = new File(dataDir).listFiles()

        for (File f : files) {
            if (!f.isDirectory() && !f.isHidden() && f.exists() && f.canRead() && (filter == null || filter.accept(f)))
                indexFile(f)
        }

        return writer.numDocs()
    }

    private static class TextFilesFilter implements FileFilter {
        @Override
        boolean accept(File pathname) { pathname.getName().toLowerCase().endsWith(".txt") }
    }

    protected Document getDocument(File f) throws Exception {
        Document doc = new Document()
        doc.add(new Field("contents", new FileReader(f)))
        doc.add(new Field("filename", f.getName(), Field.Store.YES, Field.Index.NOT_ANALYZED))
        doc.add(new Field("fullpath", f.getCanonicalPath(), Field.Store.YES, Field.Index.NOT_ANALYZED))
        return doc
    }

    private void indexFile(File f) throws Exception {
        System.out.println("Indexing " + f.getCanonicalPath())
        Document doc = getDocument(f)
        writer.addDocument(doc)
    }
}
