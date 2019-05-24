/*
################################################################################
#  THIS FILE IS 100% GENERATED BY ZPROJECT; DO NOT EDIT EXCEPT EXPERIMENTALLY  #
#  Read the zproject/README.md for information about making permanent changes. #
################################################################################
*/
package org.zeromq.czmq;

import java.util.stream.Stream;
import org.scijava.nativelib.NativeLoader;

public class Zhashx implements AutoCloseable{
    static {
        if (System.getProperty("java.vm.vendor").contains("Android")) {
            System.loadLibrary("czmqjni");
        } else {
            Stream.of(
                "zmq",
                "uuid",
                "libsystemd",
                "lz4",
                "curl",
                "microhttpd",
                "czmq"
            )
            .forEach(lib -> {
                try {
                    NativeLoader.loadLibrary(lib);
                } catch (Exception e) {
                    System.err.println("[WARN] " + e.getMessage() +" from jar. Assuming it is installed on the system.");
                }
            });
            try {
                NativeLoader.loadLibrary("czmqjni");
            } catch (Exception e) {
                System.exit (-1);
            }
        }
    }
    public long self;
    /*
    Create a new, empty hash container
    */
    native static long __new ();
    public Zhashx () {
        /*  TODO: if __new fails, self is null...            */
        self = __new ();
    }
    public Zhashx (long pointer) {
        self = pointer;
    }
    /*
    Unpack binary frame into a new hash table. Packed data must follow format
    defined by zhashx_pack. Hash table is set to autofree. An empty frame
    unpacks to an empty hash table.
    */
    native static long __unpack (long frame);
    public static Zhashx unpack (Zframe frame) {
        return new Zhashx (__unpack (frame.self));
    }
    /*
    Destroy a hash container and all items in it
    */
    native static void __destroy (long self);
    @Override
    public void close () {
        __destroy (self);
        self = 0;
    }
    /*
    Insert item into hash table with specified key and item.
    If key is already present returns -1 and leaves existing item unchanged
    Returns 0 on success.
    */
    native static int __insert (long self, long key, long item);
    public int insert (long key, long item) {
        return __insert (self, key, item);
    }
    /*
    Update or insert item into hash table with specified key and item. If the
    key is already present, destroys old item and inserts new one. If you set
    a container item destructor, this is called on the old value. If the key
    was not already present, inserts a new item. Sets the hash cursor to the
    new item.
    */
    native static void __update (long self, long key, long item);
    public void update (long key, long item) {
        __update (self, key, item);
    }
    /*
    Remove an item specified by key from the hash table. If there was no such
    item, this function does nothing.
    */
    native static void __delete (long self, long key);
    public void delete (long key) {
        __delete (self, key);
    }
    /*
    Delete all items from the hash table. If the key destructor is
    set, calls it on every key. If the item destructor is set, calls
    it on every item.
    */
    native static void __purge (long self);
    public void purge () {
        __purge (self);
    }
    /*
    Return the item at the specified key, or null
    */
    native static long __lookup (long self, long key);
    public long lookup (long key) {
        return __lookup (self, key);
    }
    /*
    Reindexes an item from an old key to a new key. If there was no such
    item, does nothing. Returns 0 if successful, else -1.
    */
    native static int __rename (long self, long oldKey, long newKey);
    public int rename (long oldKey, long newKey) {
        return __rename (self, oldKey, newKey);
    }
    /*
    Return the number of keys/items in the hash table
    */
    native static long __size (long self);
    public long size () {
        return __size (self);
    }
    /*
    Return a zlistx_t containing the keys for the items in the
    table. Uses the key_duplicator to duplicate all keys and sets the
    key_destructor as destructor for the list.
    */
    native static long __keys (long self);
    public Zlistx keys () {
        return new Zlistx (__keys (self));
    }
    /*
    Return a zlistx_t containing the values for the items in the
    table. Uses the duplicator to duplicate all items and sets the
    destructor as destructor for the list.
    */
    native static long __values (long self);
    public Zlistx values () {
        return new Zlistx (__values (self));
    }
    /*
    Simple iterator; returns first item in hash table, in no given order,
    or NULL if the table is empty. This method is simpler to use than the
    foreach() method, which is deprecated. To access the key for this item
    use zhashx_cursor(). NOTE: do NOT modify the table while iterating.
    */
    native static long __first (long self);
    public long first () {
        return __first (self);
    }
    /*
    Simple iterator; returns next item in hash table, in no given order,
    or NULL if the last item was already returned. Use this together with
    zhashx_first() to process all items in a hash table. If you need the
    items in sorted order, use zhashx_keys() and then zlistx_sort(). To
    access the key for this item use zhashx_cursor(). NOTE: do NOT modify
    the table while iterating.
    */
    native static long __next (long self);
    public long next () {
        return __next (self);
    }
    /*
    After a successful first/next method, returns the key for the item that
    was returned. This is a constant string that you may not modify or
    deallocate, and which lasts as long as the item in the hash. After an
    unsuccessful first/next, returns NULL.
    */
    native static long __cursor (long self);
    public long cursor () {
        return __cursor (self);
    }
    /*
    Add a comment to hash table before saving to disk. You can add as many
    comment lines as you like. These comment lines are discarded when loading
    the file. If you use a null format, all comments are deleted.
    */
    native static void __comment (long self, String format);
    public void comment (String format) {
        __comment (self, format);
    }
    /*
    Save hash table to a text file in name=value format. Hash values must be
    printable strings; keys may not contain '=' character. Returns 0 if OK,
    else -1 if a file error occurred.
    */
    native static int __save (long self, String filename);
    public int save (String filename) {
        return __save (self, filename);
    }
    /*
    Load hash table from a text file in name=value format; hash table must
    already exist. Hash values must printable strings; keys may not contain
    '=' character. Returns 0 if OK, else -1 if a file was not readable.
    */
    native static int __load (long self, String filename);
    public int load (String filename) {
        return __load (self, filename);
    }
    /*
    When a hash table was loaded from a file by zhashx_load, this method will
    reload the file if it has been modified since, and is "stable", i.e. not
    still changing. Returns 0 if OK, -1 if there was an error reloading the
    file.
    */
    native static int __refresh (long self);
    public int refresh () {
        return __refresh (self);
    }
    /*
    Serialize hash table to a binary frame that can be sent in a message.
    The packed format is compatible with the 'dictionary' type defined in
    http://rfc.zeromq.org/spec:35/FILEMQ, and implemented by zproto:

       ; A list of name/value pairs
       dictionary      = dict-count *( dict-name dict-value )
       dict-count      = number-4
       dict-value      = longstr
       dict-name       = string

       ; Strings are always length + text contents
       longstr         = number-4 *VCHAR
       string          = number-1 *VCHAR

       ; Numbers are unsigned integers in network byte order
       number-1        = 1OCTET
       number-4        = 4OCTET

    Comments are not included in the packed data. Item values MUST be
    strings.
    */
    native static long __pack (long self);
    public Zframe pack () {
        return new Zframe (__pack (self));
    }
    /*
    Make a copy of the list; items are duplicated if you set a duplicator
    for the list, otherwise not. Copying a null reference returns a null
    reference. Note that this method's behavior changed slightly for CZMQ
    v3.x, as it does not set nor respect autofree. It does however let you
    duplicate any hash table safely. The old behavior is in zhashx_dup_v2.
    */
    native static long __dup (long self);
    public Zhashx dup () {
        return new Zhashx (__dup (self));
    }
    /*
    Make copy of hash table; if supplied table is null, returns null.
    Does not copy items themselves. Rebuilds new table so may be slow on
    very large tables. NOTE: only works with item values that are strings
    since there's no other way to know how to duplicate the item value.
    */
    native static long __dupV2 (long self);
    public Zhashx dupV2 () {
        return new Zhashx (__dupV2 (self));
    }
    /*
    Self test of this class.
    */
    native static void __test (boolean verbose);
    public static void test (boolean verbose) {
        __test (verbose);
    }
}
