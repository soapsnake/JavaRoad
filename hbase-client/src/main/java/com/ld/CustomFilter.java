package com.ld;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.filter.BinaryComparator;
import org.apache.hadoop.hbase.filter.ByteArrayComparable;
import org.apache.hadoop.hbase.filter.ColumnPaginationFilter;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.FamilyFilter;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.PageFilter;
import org.apache.hadoop.hbase.filter.PrefixFilter;
import org.apache.hadoop.hbase.filter.QualifierFilter;
import org.apache.hadoop.hbase.filter.RegexStringComparator;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.filter.SubstringComparator;
import org.apache.hadoop.hbase.filter.TimestampsFilter;
import org.apache.hadoop.hbase.filter.ValueFilter;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 各种各样的hbase过滤器
 * Created by liudun on 2017/9/20.
 */
public class CustomFilter {

    /**
     * 比较过滤器,只返回过滤器匹配的值
     *
     * @param value
     * @return
     */
    public static Filter getCompareFilter(byte[] value) {

        CompareFilter.CompareOp compareOp = CompareFilter.CompareOp.GREATER_OR_EQUAL;


        ByteArrayComparable comparator = new ByteArrayComparable(value) {
            @Override
            public byte[] toByteArray() {
                return new byte[0];
            }

            @Override
            public int compareTo(byte[] bytes, int i, int i1) {
                return 0;
            }
        };

        Filter compareFilter = new CompareFilter(compareOp, comparator) {
            @Override
            public ReturnCode filterKeyValue(Cell cell) throws IOException {
                return null;
            }
        };

        return compareFilter;
    }

    /**
     * 行过滤器,返回被过滤器过滤后的值
     *
     * @return
     */
    public static Filter getRowFilter() {
        //使用正则表达式筛选需要返回的行.
        Filter rowFilter = new RowFilter(CompareFilter.CompareOp.EQUAL, new RegexStringComparator(".*-.5"));

        return rowFilter;
    }

    /**
     * 列族过滤器s
     *
     * @return
     */
    public static Filter getFamilyFilter() {
        Filter familyFilter = new FamilyFilter(CompareFilter.CompareOp.LESS, new BinaryComparator(Bytes.toBytes("colfam3")));

        return familyFilter;
    }

    /**
     * 列名过滤器(单独某列)
     *
     * @return
     */
    public static Filter getColumFilter() {
        Filter columFilter = new QualifierFilter(CompareFilter.CompareOp.LESS_OR_EQUAL, new BinaryComparator(Bytes.toBytes("col2-1")));

        return columFilter;
    }

    /**
     * 单元格值过滤器
     * 注意: 特定的匹配(第二个参数)只能与特定的比较运算符配合
     *
     * @return
     */
    public static Filter getKeyValueFilter() {
        Filter keyValueFilter = new ValueFilter(CompareFilter.CompareOp.EQUAL, new SubstringComparator(".4"));

        return keyValueFilter;
    }

    /**
     * 前缀过滤器.返回与前缀匹配的行
     *
     * @return
     */
    public static Filter getPreFixFilter() {
        Filter preFixFilter = new PrefixFilter(Bytes.toBytes("row-1"));

        return preFixFilter;
    }

    /**
     * 分页过滤器
     *结果可能不准确
     * @param pageSize
     * @return
     */
    public static Filter getPageFilter(long pageSize) {
        Filter pageFilter = new PageFilter(pageSize);
        return pageFilter;
    }

    /**
     * 时间戳过滤器,可以返回精确匹配这些时间戳的数据
     * @param timestamps
     * @return
     */
    public static Filter getTimeStampFilter(List<Long> timestamps) {
        Filter timeStampFilter = new TimestampsFilter(timestamps);
        return timeStampFilter;
    }

    /**
     * 列分页过滤器,可以把一行中的所有列分页返回
     * @param limit
     * @param offset
     * @return
     */
    public static Filter getColumnPaginationFilter(int limit, int offset) {
        Filter columnPaginationFilter = new ColumnPaginationFilter(limit, offset);
        return columnPaginationFilter;
    }

    /**
     * 多过滤器,MUST_PASS_ALL 代表返回值必须经过所有过滤器,取所有过滤器结果的交集
     * @return
     */
    public static Filter getFilterList(){
        List<Filter> filters = new ArrayList<>();
        filters.add(getPageFilter(100L));
        filters.add(getRowFilter());

        FilterList filterList = new FilterList(FilterList.Operator.MUST_PASS_ALL, filters);

        return filterList;
    }

}